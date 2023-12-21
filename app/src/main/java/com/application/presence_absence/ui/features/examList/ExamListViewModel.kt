package com.application.presence_absence.ui.features.examList

import androidx.lifecycle.ViewModel
import com.application.presence_absence.ui.features.examList.entities.ExamDay
import com.application.presence_absence.ui.features.examList.entities.ExamFilterStateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class ExamListViewModel @Inject constructor() : ViewModel() {

    private val _filter = MutableStateFlow(ExamFilterStateView())
    val filter = _filter.asStateFlow()

    fun setExamPlace(examPlace: List<String>) {
        _filter.value = _filter.value.copy(examPlace = examPlace)
    }

    fun setExamDay(examDay: List<ExamDay>) {
        _filter.value = _filter.value.copy(examDay = examDay)
    }

    fun setExamState(examState: List<String>) {
        _filter.value = _filter.value.copy(examState = examState)
    }

}