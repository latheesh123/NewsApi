package latheesh.com.news_api.Utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import latheesh.com.news_api.model.DataResource
import latheesh.com.news_api.ui.CustomRecylerView


/**
 * Syntactic sugar for [LiveData.observe] function where the [Observer] is the last parameter.
 * Hence can be passed outside the function parenthesis.
 */
inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner, Observer { it?.apply(observer) })
}

/**
 * Eliminates the boiler plate on the UI when dealing with `LiveData<Resource<T>>`
 * type from `Repository`.
 * It internally updates the [list] based upon the status and executes
 * the [f] only if status is either SUCCESS or ERROR.
 */
fun <ResultType> DataResource<ResultType>.load(list: CustomRecylerView, f: (ResultType?) -> Unit) {
    list.showState(dataStatus)
    load(f)
}

/**
 * Eliminates the boiler plate on the UI when dealing with `LiveData<Resource<T>>`
 * type from `Repository`.
 * It internally executes the [f] only if status is either SUCCESS or ERROR.
 */
fun <ResultType> DataResource<ResultType>.load(f: (ResultType?) -> Unit) {
    if (!dataStatus.isLoading()) {
        f(data)
    }
}