package com.hercan.harrypotterinfoapp.network.utils

data class Resource<T>(val status: Status, val data: T? = null, val message: String? = null) {
    companion object {
        fun <T> success(data: T? = null, message: String? = null) =
            Resource<T>(Status.SUCCESS, data, message)

        fun <T> error(status: Status = Status.ERROR, message: String) =
            Resource<T>(status, message = message)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
}