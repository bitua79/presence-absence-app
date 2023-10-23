package com.application.presence_absence.ui.examList.filter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class FilterViewModel : ViewModel() {

    private val _filter = MutableStateFlow(ExamFilterView())
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