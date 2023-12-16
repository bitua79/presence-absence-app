package com.application.presence_absence.core.utils

import com.application.presence_absence.core.entities.AppError
import com.application.presence_absence.core.entities.AppException
import com.application.presence_absence.core.entities.ErrorResult
import com.application.presence_absence.core.entities.Resource
import com.application.presence_absence.core.entities.Result
import com.application.presence_absence.core.entities.Success
import com.application.presence_absence.core.extensions.toResult
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
            AppException.RemoteDataSourceException(
                AppError(
                    message = "Request Timeout"
                )
            )
        )
    } catch (ex: Exception) {
        // An exception was thrown when calling the API so we're converting this to an IOException
        ex.printStackTrace()
        ErrorResult(AppException.IOException(ex))
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
    } else ErrorResult(AppException.NetworkConnectionException())
}