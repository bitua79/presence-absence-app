package com.application.presence_absence.ui.features.examList

import androidx.lifecycle.viewModelScope
import com.application.presence_absence.domain.usecases.GetExamList
import com.application.presence_absence.ui.features.examList.entities.ExamDay
import com.application.presence_absence.ui.features.examList.entities.ExamFilterStateView
import com.application.presence_absence.ui.features.examList.entities.ExamListViewState
import com.application.presence_absence.ui.widgets.UiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExamListViewModel @Inject constructor(
    private val getExamList: GetExamList,
) : UiStateViewModel() {

    private val _dataViewState = MutableStateFlow(ExamListViewState())
    val dataViewState = _dataViewState.asStateFlow()

    init {
        viewModelScope.launch {
            useCaseInvoker(useCase = { getExamList() }, dataStateReady = { list ->
                _dataViewState.update {
                    val examList = list.map { item -> item.toExamView() }
                    it.copy(allList = examList, filteredList = examList)
                }
            })
        }
    }

    private val _filter = MutableStateFlow(ExamFilterStateView())
    val filter = _filter.asStateFlow()

    fun setExamPlace(examPlace: List<String>) {
        _filter.value = _filter.value.copy(examPlace = examPlace)
        actFilters()
    }

    fun setExamDay(examDay: List<ExamDay>) {
        _filter.value = _filter.value.copy(examDay = examDay)
        actFilters()
    }

    fun setExamState(examState: List<String>) {
        _filter.value = _filter.value.copy(examState = examState)
        actFilters()
    }

    fun setExamQuery(query: String) {
        _filter.value = _filter.value.copy(examQuery = query)
        actFilters()
    }

    private fun actFilters() {
        val examFilter = _filter.value
        // Update list
        val faculty = examFilter.examPlace
        val day = examFilter.examDay.map { it.n_th }
        val state = examFilter.examState
        val query = examFilter.examQuery

        var filteredList = _dataViewState.value.allList.orEmpty()

        if (faculty.isNotEmpty()) {
            filteredList = filteredList.filter {
                faculty.contains(it.faculty)
            }
        }

        if (day.isNotEmpty()) {
            filteredList = filteredList.filter {
                day.contains(it.day)
            }
        }

        if (state.isNotEmpty()) {
            filteredList = filteredList.filter {
                state.contains(it.state.title)
            }
        }

        if (query.isNotEmpty()) {
            filteredList = filteredList.filter {
                it.name.contains(query)
            }
        }

        _dataViewState.update { it.copy(filteredList = filteredList) }
    }
}