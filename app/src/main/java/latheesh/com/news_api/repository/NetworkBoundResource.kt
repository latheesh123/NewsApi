package latheesh.com.news_api.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import latheesh.com.news_api.Utils.AppExecutors
import latheesh.com.news_api.model.DataResource

abstract class NetworkBoundResource<ResultType, RequestType> @MainThread constructor(
    private val appExecutors: AppExecutors
) {


    private val result = MediatorLiveData<DataResource<ResultType?>>()

    init {
        // Send loading state to UI
        result.value = DataResource.loading()
        val dbSource = this.loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData -> setValue(DataResource.success(newData)) }
            }
        }
    }


    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource) { result.setValue(DataResource.loading()) }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            response?.apply {
                if (dataStatus.isSuccessful()) {
                    appExecutors.diskIO().execute {
                        processResponse(this)?.let { requestType -> saveCallResult(requestType) }
                        appExecutors.mainThread().execute {
                            // we specially request a new live data,
                            // otherwise we will get immediately last cached value,
                            // which may not be updated with latest results received from network.
                            result.addSource(loadFromDb()) { newData -> setValue(DataResource.success(newData)) }
                        }
                    }
                } else {
                    onFetchFailed()
                    result.addSource(dbSource) { result.setValue(DataResource.error(errmessage)) }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: DataResource<ResultType?>) {
        if (result.value != newValue) result.value = newValue
    }

    protected fun onFetchFailed() {}

    fun asLiveData(): LiveData<DataResource<ResultType?>> {
        return result
    }

    @WorkerThread
    private fun processResponse(response: DataResource<RequestType>): RequestType? {
        return response.data
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<DataResource<RequestType>>
}