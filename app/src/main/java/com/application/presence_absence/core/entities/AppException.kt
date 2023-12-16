package com.application.presence_absence.core.entities


sealed class AppException {

    data class IOException(val cause: Throwable) : AppException()

    data class NetworkConnectionException(val message: String = "Network Connection Error") :
        AppException()

    data class RemoteDataSourceException(val error: AppError?) : AppException()
}