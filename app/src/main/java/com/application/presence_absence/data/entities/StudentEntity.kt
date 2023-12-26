package com.application.presence_absence.data.entities

data class StudentEntity(
    val first_name: String,
    val id: Int,
    val last_name: String,
    val national_code: String,
    val profile: String,
    val status: Int,
    val student_id: String
)