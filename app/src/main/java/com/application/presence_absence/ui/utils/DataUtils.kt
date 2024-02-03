package com.application.presence_absence.ui.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.application.presence_absence.core.entities.AppException
import com.application.presence_absence.ui.core.DefaultError
import com.application.presence_absence.ui.core.IOExceptionError
import com.application.presence_absence.ui.core.InvalidInputError
import com.application.presence_absence.ui.core.NoInternetError
import com.application.presence_absence.ui.core.UiError
import com.application.presence_absence.ui.core.UnAuthorizedError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

// Convert AppException to UiError
fun AppException.getError(): UiError {
    var message: UiError = DefaultError
    when (this) {
        is AppException.IOException -> {
            message = IOExceptionError
        }

        is AppException.NetworkConnectionException -> {
            message = NoInternetError
        }

        is AppException.RemoteDataSourceException -> {
            when (code) {
                401 -> {
                    message = UnAuthorizedError
                }

                500 -> {
                    message = InvalidInputError
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