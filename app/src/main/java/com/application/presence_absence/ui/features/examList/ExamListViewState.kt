package com.application.presence_absence.ui.features.examList

import com.application.presence_absence.ui.features.examList.entities.ExamView

data class ExamListViewState(
    val allList: List<ExamView>? = emptyList(),
    val filteredList: List<ExamView>? = emptyList()
)