package com.application.presence_absence.ui.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.application.presence_absence.core.entities.AppException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

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

inline fun <T> Flow<T>.collectOnFragment(fragment: Fragment, crossinline onCollect: (T) -> Unit) {
    fragment.lifecycleScope.launchWhenStarted {
        this@collectOnFragment.collectLatest {
            onCollect(it)
        }
    }
}