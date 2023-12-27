package com.application.presence_absence.ui.features.examList.entities

data class ExamFilterStateView(
    val examPlace: List<String> = listOf(),
    val examDay: List<ExamDay> = listOf(),
    val examState: List<String> = listOf(),
    val examQuery: String = ""
)