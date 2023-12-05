package com.application.presence_absence.ui.studentList

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class StudentListViewModel : ViewModel() {

    private val _filter = MutableStateFlow(StudentFilterView())
    val filter = _filter.asStateFlow()

    fun setShowPresents(boolean: Boolean?) {
        _filter.value = _filter.value.copy(showPresents = boolean)
    }

    fun setShowAbsents(boolean: Boolean?) {
        _filter.value = _filter.value.copy(showAbsents = boolean)
    }
}