package com.application.presence_absence.ui.features.studentList.entities

data class StudentFilterViewState(
    val showPresents: Boolean? = null,
    val showAbsents: Boolean? = null,
    val studentQuery: String = ""
)