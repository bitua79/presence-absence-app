package com.application.presence_absence.ui.features.examList.entities

enum class ExamStatus(val title: String) {
    IN_PROGRESS("در حال برگزاری"),
    NOT_STARTED("شروع نشده"),
    CANCELLED("لغو شده"),
    FINISHED("تمام شده");
}