package com.application.presence_absence.ui.features.examList

import androidx.lifecycle.viewModelScope
import com.application.presence_absence.domain.usecases.GetExamList
import com.application.presence_absence.ui.core.UiStateViewModel
import com.application.presence_absence.ui.features.examList.entities.ExamDay
import com.application.presence_absence.ui.features.examList.entities.ExamFilterViewState
import com.application.presence_absence.ui.features.examList.entities.ExamListViewState
import com.application.presence_absence.ui.features.examList.entities.ExamView
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

    fun getAllExamList() {
        resetViewState()
        viewModelScope.launch {
            useCaseInvoker(useCase = { getExamList() }, dataStateReady = { list ->
                _dataViewState.update {
                    val examList = list.map { item -> item.toExamView() }
                    val filtered = getFilteredList(examList)
                    it.copy(allList = examList, filteredList = filtered)
                }
            }, onError = {
                _dataViewState.update {
                    it.copy(allList = emptyList(), filteredList = emptyList())
                }
            })
        }
    }

    private fun resetViewState() {
        _dataViewState.update {
            it.copy(allList = null, filteredList = null)
        }
    }

    private val _filter = MutableStateFlow(ExamFilterViewState())
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

    fun setExamQuery(query: String?) {
        if (query == null) return
        _filter.value = _filter.value.copy(examQuery = query)
        actFilters()
    }

    private fun getFilteredList(list: List<ExamView> = _dataViewState.value.allList.orEmpty()): List<ExamView> {
        val examFilter = _filter.value
        // Update list
        val faculty = examFilter.examPlace
        val day = examFilter.examDay
        val state = examFilter.examState
        val query = examFilter.examQuery

        var filteredList = list

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
                state.contains(it.status.title)
            }
        }

        if (query.isNotEmpty()) {
            filteredList = filteredList.filter {
                it.name.contains(query)
            }
        }

        return filteredList
    }

    fun actFilters() {
        _dataViewState.update { it.copy(filteredList = getFilteredList()) }
    }
}