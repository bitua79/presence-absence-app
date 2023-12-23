package com.application.presence_absence.ui.utils

import com.application.presence_absence.core.entities.AppException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import saman.zamani.persiandate.PersianDate


suspend fun <T> runIO(block: suspend () -> T): T {
    return withContext(Dispatchers.IO) {
        block()
    }
}

// Convert AppException to UiError
fun AppException.getError(): String {
    var message = "خطا در ارتباط با سرور"
    when (this) {
        is AppException.IOException -> {
            message = "ارتباط با سرور برقرار نمی باشد"
        }

        is AppException.NetworkConnectionException -> {
            message = "عدم اتصال به اینترنت"
        }

        is AppException.RemoteDataSourceException -> {
            when (code) {
                401 -> {
                    message = "نام و یا پسورد اشتباه است"
                }

                500 -> {
                    message = "داده های ورودی نامعتبرند"
                }
            }
        }
    }
    return message
}

fun PersianDate.getShDate(): String {
    return "${dayName()} $shMonth/$shDay"
}