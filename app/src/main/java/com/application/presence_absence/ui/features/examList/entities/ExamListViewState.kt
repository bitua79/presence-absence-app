package com.application.presence_absence.ui.features.examList.entities

data class ExamListViewState(
    val allList: List<ExamView>? = null,
    val filteredList: List<ExamView>? = null
)