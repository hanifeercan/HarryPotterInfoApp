package com.hercan.harrypotterinfoapp.network.utils

import retrofit2.Response

abstract class NetworkExt {
    suspend fun <T> safeApiCall(call: suspend () -> Response<List<T>>): Resource<List<T>> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (!body.isNullOrEmpty()) {
                    return Resource.success(body)
                } else {
                    return Resource.error(
                        Status.ERROR,
                        "Opps! Something went wrong."
                    )
                }
            } else {
                return Resource.error(Status.ERROR, "Api call failed")
            }
        } catch (e: Exception) {
            return Resource.error(Status.ERROR, "Api call failed")
        }
    }
}