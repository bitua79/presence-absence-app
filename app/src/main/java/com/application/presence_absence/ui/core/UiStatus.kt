package com.application.presence_absence.ui.core

import androidx.annotation.StringRes
import com.application.presence_absence.R


sealed class UiStatus

// Does not have tag
object UiIdle : UiStatus()

object UiLoading : UiStatus()

open class UiError(@StringRes val errorStringId: Int) : UiStatus()

object NoInternetError : UiError(R.string.msg_no_internet_connection)
object IOExceptionError : UiError(R.string.msg_service_process_failed)
object UnAuthorizedError : UiError(R.string.msg_unauthorized)
object InvalidInputError : UiError(R.string.msg_invalid_input)
object DefaultError : UiError(R.string.msg_connection_failed)

object UiSuccess : UiStatus()
