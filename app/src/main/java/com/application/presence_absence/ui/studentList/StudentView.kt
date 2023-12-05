package com.application.presence_absence.ui.studentList

data class StudentView(
    val name: String,
    val iconUrl: String?,
    val idNumber: String,
    var attendance: StudentAttendance
)