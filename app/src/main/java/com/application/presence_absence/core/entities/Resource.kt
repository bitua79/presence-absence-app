package com.application.presence_absence.core.entities


data class Resource<out T> @JvmOverloads constructor(
    val data: T? = null
)