package com.application.presence_absence.ui.examList.filter

data class ExamFilterView(
    val examPlace: List<String> = listOf(),
    // TODO: update after implement
    val examTime: String? = null,
    val examState: List<String> = listOf()
)