package com.application.presence_absence.ui.studentList

data class StudentView(
    val name: String,
    val iconUrl: String?,
    val idNumber:String,
    var presence: Attendance
){

    fun setAttendance(attendance: Attendance){
        presence = attendance
    }

}