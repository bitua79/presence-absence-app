@file:Suppress("NOTHING_TO_INLINE")

package com.application.presence_absence.core.extensions

import com.application.presence_absence.core.entities.*
import com.application.presence_absence.core.entities.AppException.*
import com.application.presence_absence.core.utils.NetworkHandler
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException


/**
 * Calls [call] request and convert response<resource<T>> to result<T>
 *     handle `Timeout exception` and `IO exception` (when calling the API)
 **/
suspend fun <T, R : Resource<T>> safeCall(call: suspend () -> Response<R>): Result<T> {
    return try {
        val response = call()
        response.toResult()
    } catch (ex: SocketTimeoutException) {
        ErrorResult(
            RemoteDataSourceException(
                message = "Request Timeout"
            )
        )
    } catch (ex: Exception) {
        // An exception was thrown when calling the API so we're converting this to an IOException
        ex.printStackTrace()
        ErrorResult(IOException(ex))
    }
}

/** Parse Result getting from [action] to Success/Error
 * and also check network connection by using [networkHandler]
 * when we need to do something on [Success] */
suspend fun <V, R> makeRequest(
    networkHandler: NetworkHandler,
    action: suspend () -> Result<V>,
    onSuccess: suspend Success<V>.() -> Result<R>
): Result<R> {

    return if (networkHandler.hasNetworkConnection()) {
        when (val result = action()) {
            is Success<V> -> onSuccess(result)
            is ErrorResult<V> -> ErrorResult(result.exception)
        }
    } else ErrorResult(NetworkConnectionException())
}

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
            ErrorResult(getError())
        }
    } else {
        ErrorResult(getError())
    }
} catch (e: Exception) {
    ErrorResult(IOException(e))
}

fun <T> Response<T>.getError(): RemoteDataSourceException {

    val errorBody: String? = this.errorBody()?.string()
    val responseCode: Int = code()

    if (errorBody == null) {
        return RemoteDataSourceException()
    }

    try {
        val errorBodyObject = JSONObject(errorBody)
        val errorMessage = errorBodyObject.optString("message").orEmpty()

        return RemoteDataSourceException(code = responseCode, message = errorMessage)

    } catch (e: Exception) {
        if (e is JSONException && responseCode == 501) {
            return RemoteDataSourceException(code = 501)
        }
        return RemoteDataSourceException()
    }
}
