package com.application.presence_absence.ui.examList

import com.application.presence_absence.ui.examList.filter.state.ExamState

object FakeExamList {
    val list = listOf(
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "سوم", hour = "10", studentCount = "46", state = ExamState.CANCELLED),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "دوم", hour = "10", studentCount = "46", state = ExamState.IN_PROGRESS),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "صفرم", hour = "10", studentCount = "46", state = ExamState.NOT_STARTED),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "هفتم", hour = "10", studentCount = "46", state = ExamState.FINISHED),
        ExamView(
            name = "ریاضی",
            collegeName = "دانشکده مهندسی6",
            className = "B35",
            day = "سه صفرم",
            hour = "6",
            studentCount = "46",
            state = ExamState.CANCELLED
        ),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "سوم", hour = "5", studentCount = "46", state = ExamState.CANCELLED),
        ExamView(
            name = "ریاضی",
            collegeName = "دانشکده مهندسی6",
            className = "B35",
            day = "سوم",
            hour = "10",
            studentCount = "46",
            state = ExamState.CANCELLED
        ),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "سوم", hour = "12", studentCount = "46", state = ExamState.CANCELLED),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "سوم", hour = "10", studentCount = "46", state = ExamState.CANCELLED),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "سوم", hour = "11", studentCount = "46", state = ExamState.CANCELLED),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "سوم", hour = "10", studentCount = "46", state = ExamState.CANCELLED),
        ExamView(
            name = "ریاضی",
            collegeName = "دانشکده مهندسی6",
            className = "B35",
            day = "سوم",
            hour = "7",
            studentCount = "46",
            state = ExamState.CANCELLED
        ),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "سوم", hour = "10", studentCount = "46", state = ExamState.CANCELLED),
    )
}