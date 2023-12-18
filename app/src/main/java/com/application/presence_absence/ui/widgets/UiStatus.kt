package com.application.presence_absence.ui.widgets


sealed class UiStatus

// Does not have tag
object UiIdle : UiStatus()

object UiLoading : UiStatus()

data class UiError(val message: String) : UiStatus()

object UiSuccess : UiStatus()
