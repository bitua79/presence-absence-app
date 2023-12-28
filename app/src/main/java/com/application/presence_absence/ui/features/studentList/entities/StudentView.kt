package com.application.presence_absence.ui.features.studentList.entities

data class StudentView(
    val id: Int,
    val name: String,
    val iconUrl: String?,
    val idNumber: String,
    var status: StudentStatus
) {
    fun setAttendance(attendance: StudentStatus) {
        this.status = attendance
    }
}