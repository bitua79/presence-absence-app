package com.application.presence_absence.ui.features.studentList

import androidx.lifecycle.viewModelScope
import com.application.presence_absence.core.entities.Constants
import com.application.presence_absence.domain.params.PostStatus
import com.application.presence_absence.domain.usecases.GetStudentList
import com.application.presence_absence.domain.usecases.SetExamStatus
import com.application.presence_absence.domain.usecases.SetStudentStatus
import com.application.presence_absence.ui.features.studentList.entities.StudentFilterViewState
import com.application.presence_absence.ui.features.studentList.entities.StudentListViewState
import com.application.presence_absence.ui.features.studentList.entities.StudentStatus
import com.application.presence_absence.ui.widgets.UiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val getStudentList: GetStudentList,
    private val setStudentStatus: SetStudentStatus,
    private val setExamStatus: SetExamStatus
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

    fun setStudentAttendanceStatus(
        examId: String,
        studentId: String,
        state: StudentStatus
    ) {
        viewModelScope.launch {
            useCaseInvoker(useCase = {
                setStudentStatus(
                    examId,
                    studentId,
                    PostStatus(state.numValue)
                )
            })
        }
    }

    // After all student taken attendance, finalize exam status
    // to 1(can not be changed exam status or student attendance)
    fun invokeExamStatus(
        examId: String
    ) {
        viewModelScope.launch {
            useCaseInvoker(useCase = {
                setExamStatus(
                    examId,
                    PostStatus(Constants.EXAM_TAKEN_ATTENDANCE)
                )
            })
        }
    }

    private val _filter = MutableStateFlow(StudentFilterViewState())
    val filter = _filter.asStateFlow()

    fun setShowPresents(boolean: Boolean?) {
        _filter.value = _filter.value.copy(showPresents = boolean)
        actFilters()
    }

    fun setShowAbsents(boolean: Boolean?) {
        _filter.value = _filter.value.copy(showAbsents = boolean)
        actFilters()
    }

    fun setStudentQuery(query: String) {
        _filter.value = _filter.value.copy(studentQuery = query)
        actFilters()
    }

    private fun actFilters() {
        val filterState = _filter.value
        val presence = filterState.showPresents
        val absence = filterState.showAbsents
        val query = filterState.studentQuery

        var filteredList = _dataViewState.value.allList.orEmpty()

        if (presence == true) {
            filteredList = filteredList.filter {
                it.status == StudentStatus.PRESENCE
            }
        }

        if (absence == true) {
            filteredList = filteredList.filter {
                it.status == StudentStatus.ABSENCE
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