package latheesh.com.news_api.Utils

import latheesh.com.news_api.model.DataResource
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Synthetic sugaring to create Retrofit Service.
 */
inline fun <reified T> Retrofit.create(): T = create(T::class.java)

/**
 * Converts Retrofit [Response] to [Resource] which provides state
 * and data to the UI.
 */
fun <ResultType> Response<ResultType>.toResource(): DataResource<ResultType> {
    val error = errorBody()?.toString() ?: message()
    return when {
        isSuccessful -> {
            val body = body()
            when {
                body != null -> DataResource.success(body)
                else -> DataResource.error(error)
            }
        }
        else -> DataResource.error(error)
    }
}