package latheesh.com.news_api.di

import androidx.lifecycle.LiveData
import latheesh.com.news_api.Utils.toResource
import latheesh.com.news_api.model.DataResource
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<DataResource<R>>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): LiveData<DataResource<R>> {
        return object : LiveData<DataResource<R>>() {
            internal var started = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(response.toResource())
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(DataResource.error(throwable.message))
                        }
                    })
                }
            }
        }
    }
}
