package com.application.presence_absence.ui.features.examList

import androidx.lifecycle.ViewModel
import com.application.presence_absence.ui.features.examList.entities.ExamFilterStateView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class ExamListViewModel : ViewModel() {

    private val _filter = MutableStateFlow(ExamFilterStateView())
    val filter = _filter.asStateFlow()

    fun setExamPlace(examPlace: List<String>) {
        _filter.value = _filter.value.copy(examPlace = examPlace)
    }

    // TODO: update after implement
    fun setExamTime(examTime: String?) {
        _filter.value = _filter.value.copy(examTime = examTime)
    }

    fun setExamState(examState: List<String>) {
        _filter.value = _filter.value.copy(examState = examState)
    }

}