package com.application.presence_absence.ui.features.studentList.entities

data class StudentListViewState(
    val allList: List<StudentView>? = emptyList(),
    val filteredList: List<StudentView>? = emptyList()
)