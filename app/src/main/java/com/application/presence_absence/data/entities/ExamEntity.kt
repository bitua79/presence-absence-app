package com.application.presence_absence.data.entities

data class ExamEntity(
    val id: Int,
    val title: String,
    val class_name: String,
    val faculty: String,
    val nth_day: Int,
    val date: Long,
    val time: Int,
    val status: Int
)