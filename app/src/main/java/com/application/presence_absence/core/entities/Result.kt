package com.application.presence_absence.core.entities


sealed class Result<T> {
    open fun get(): T? = null
}

data class Success<T>(val data: T) : Result<T>() {
    override fun get(): T = data
}

data class ErrorResult<T>(
    val exception: AppException,
    val data: T? = null,
) : Result<T>() {
    override fun get(): T? = data
}

/** Convert Result<[FromEntity]> to Result<[ToEntity]> by calling convert function over data */
inline fun <FromEntity, ToEntity> Result<FromEntity>.map(
    crossinline converter: (FromEntity) -> ToEntity,
): Result<ToEntity> {
    return when (this) {
        is Success -> Success(converter(data))
        is ErrorResult -> ErrorResult(exception, data?.let { converter(it) })
    }
}
