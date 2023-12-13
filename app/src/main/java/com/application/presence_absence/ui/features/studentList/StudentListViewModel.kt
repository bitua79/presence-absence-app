package com.application.presence_absence.ui.features.studentList

import androidx.lifecycle.ViewModel
import com.application.presence_absence.ui.features.studentList.entities.StudentFilterStateView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class StudentListViewModel : ViewModel() {

    private val _filter = MutableStateFlow(StudentFilterStateView())
    val filter = _filter.asStateFlow()

    fun setShowPresents(boolean: Boolean?) {
        _filter.value = _filter.value.copy(showPresents = boolean)
    }

    fun setShowAbsents(boolean: Boolean?) {
        _filter.value = _filter.value.copy(showAbsents = boolean)
    }
}