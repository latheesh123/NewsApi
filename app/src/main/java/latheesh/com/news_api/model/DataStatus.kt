package latheesh.com.news_api.model

enum class DataStatus {

    SUCCESS,
    ERROR,
    LOADING;

    fun isSuccessful()=this==SUCCESS
    fun isLoading()=this==LOADING
    fun isError()=this==ERROR

}