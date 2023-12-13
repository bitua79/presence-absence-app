package com.application.presence_absence.ui.features.examList.entities

data class ExamFilterStateView(
    val examPlace: List<String> = listOf(),
    // TODO: update after implement
    val examTime: String? = null,
    val examState: List<String> = listOf()
)