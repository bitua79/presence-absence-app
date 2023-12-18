package com.application.presence_absence.core.entities


data class Resource<out T> constructor(
    val data: T? = null
)