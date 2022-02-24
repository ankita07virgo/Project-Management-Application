package com.test.projectmanagementapplication.roomdatabase

data class Resource<out T>(val status: Status, val data:T?, val message:String?) {
    companion object{
        fun <T> success(message: String,data: T): Resource<T> = Resource(status = Status.Success, data = data, message = message)

        fun <T> error (message:String,data:T?): Resource<T> {
            return Resource(Status.Error,data,message)
        }

    }
}
