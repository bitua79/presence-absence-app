package com.application.presence_absence.ui.features.studentList

import androidx.lifecycle.viewModelScope
import com.application.presence_absence.domain.usecases.GetStudentList
import com.application.presence_absence.ui.features.studentList.entities.StudentAttendanceState
import com.application.presence_absence.ui.features.studentList.entities.StudentFilterStateView
import com.application.presence_absence.ui.features.studentList.entities.StudentListViewState
import com.application.presence_absence.ui.widgets.UiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val getStudentList: GetStudentList
) : UiStateViewModel() {

    private val _dataViewState = MutableStateFlow(StudentListViewState())
    val dataViewState = _dataViewState.asStateFlow()

    fun getAllStudents(id: String) {
        viewModelScope.launch {
            useCaseInvoker(useCase = { getStudentList(id) }, dataStateReady = { list ->
                _dataViewState.update {
                    val studentList = list.map { item -> item.toStudentView() }
                    it.copy(allList = studentList, filteredList = studentList)
                }
            })
        }
    }

    private val _filter = MutableStateFlow(StudentFilterStateView())
    val filter = _filter.asStateFlow()

    fun setShowPresents(boolean: Boolean?) {
        _filter.value = _filter.value.copy(showPresents = boolean)
        actFilters()
    }

    fun setShowAbsents(boolean: Boolean?) {
        _filter.value = _filter.value.copy(showAbsents = boolean)
        actFilters()
    }

    private fun actFilters() {
        val filterState = _filter.value
        val presence = filterState.showPresents
        val absence = filterState.showAbsents

        var filteredList = _dataViewState.value.allList.orEmpty()

        if (presence == true) {
            filteredList = filteredList.filter {
                it.attendance == StudentAttendanceState.PRESENCE
            }
        }

        if (absence == true) {
            filteredList = filteredList.filter {
                it.attendance == StudentAttendanceState.ABSENCE
            }
        }

        _dataViewState.update { it.copy(filteredList = filteredList) }
    }
}