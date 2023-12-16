@file:Suppress("NOTHING_TO_INLINE")

package com.application.presence_absence.core.extensions

import com.application.presence_absence.core.entities.*
import retrofit2.HttpException
import retrofit2.Response


/**
 * Gets response body or throws an HttpException.
 * @return Body of the request.
 */
inline fun <T> Response<T>.bodyOrThrow(): T {
    if (!isSuccessful) throw HttpException(this)
    return body()!!
}

/**
 * Gets result of response.
 * @return a Result with type of [T].
 */
inline fun <T, R : Resource<T>> Response<R>.toResult(): Result<T> = try {
    if (isSuccessful) {
        val resource: R = bodyOrThrow()
        if (resource.data != null) {
            Success(resource.data)
        } else {
            ErrorResult(AppException.RemoteDataSourceException(getError()))
        }
    } else {
        ErrorResult(AppException.RemoteDataSourceException(getError()))
    }
} catch (e: Exception) {
    ErrorResult(AppException.IOException(e))
}

fun <T> Response<T>.getError(): AppError {

    // TODO: Extract error from response json
    val errorBody: String? = this.errorBody()?.string()
    val responseCode: Int = code()

    return AppError(errorBody, responseCode.toString())
}
