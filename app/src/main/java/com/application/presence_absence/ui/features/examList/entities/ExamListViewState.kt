package com.application.presence_absence.ui.features.examList.entities

data class ExamListViewState(
    val allList: List<ExamView>? = emptyList(),
    val filteredList: List<ExamView>? = emptyList()
)