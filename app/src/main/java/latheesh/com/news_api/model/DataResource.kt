package latheesh.com.news_api.model

data class DataResource <Type>(var dataStatus: DataStatus,var data:Type?=null,var errmessage:String?=null){

    //access to internals of classes without having class instance
    companion object
    {
        fun <Type> success (data:Type):DataResource<Type> =DataResource(DataStatus.SUCCESS,data)
        fun <Type> loading ():DataResource<Type> =DataResource(DataStatus.LOADING)
        fun <Type> error (message:String?):DataResource<Type> =DataResource(DataStatus.ERROR,errmessage = message)

    }
}