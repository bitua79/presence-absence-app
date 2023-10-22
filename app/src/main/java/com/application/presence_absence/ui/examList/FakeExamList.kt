package com.application.presence_absence.ui.examList

import com.application.presence_absence.ui.examList.filter.state.ExamState

object FakeExamList {
    val list = listOf(
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "دوشنبه", hour = "10", studentCount = "46", state = ExamState.CANCELLED),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "یکشنبه", hour = "10", studentCount = "46", state = ExamState.IN_PROGRESS),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "شنبه", hour = "10", studentCount = "46", state = ExamState.NOT_STARTED),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "جمعه", hour = "10", studentCount = "46", state = ExamState.FINISHED),
        ExamView(
            name = "ریاضی",
            collegeName = "دانشکده مهندسی6",
            className = "B35",
            day = "سه شنبه",
            hour = "6",
            studentCount = "46",
            state = ExamState.CANCELLED
        ),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "دوشنبه", hour = "5", studentCount = "46", state = ExamState.CANCELLED),
        ExamView(
            name = "ریاضی",
            collegeName = "دانشکده مهندسی6",
            className = "B35",
            day = "دوشنبه",
            hour = "10",
            studentCount = "46",
            state = ExamState.CANCELLED
        ),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "دوشنبه", hour = "12", studentCount = "46", state = ExamState.CANCELLED),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "دوشنبه", hour = "10", studentCount = "46", state = ExamState.CANCELLED),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "دوشنبه", hour = "11", studentCount = "46", state = ExamState.CANCELLED),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "دوشنبه", hour = "10", studentCount = "46", state = ExamState.CANCELLED),
        ExamView(
            name = "ریاضی",
            collegeName = "دانشکده مهندسی6",
            className = "B35",
            day = "دوشنبه",
            hour = "7",
            studentCount = "46",
            state = ExamState.CANCELLED
        ),
        ExamView(name = "ریاضی", collegeName = "مهندسی", className = "B35", day = "دوشنبه", hour = "10", studentCount = "46", state = ExamState.CANCELLED),
    )
}