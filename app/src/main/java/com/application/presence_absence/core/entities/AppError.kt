package com.application.presence_absence.core.entities

data class AppError(
    var code: String? = null,
    val message: String? = null
)