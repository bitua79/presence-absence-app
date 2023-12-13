package com.application.presence_absence.ui.features.examList

import com.application.presence_absence.ui.features.examList.entities.ExamStatus
import com.application.presence_absence.ui.features.examList.entities.ExamView

object FakeExamList {
    val list = listOf(
        ExamView(
            name = "ریاضی",
            collegeName = "مهندسی",
            className = "B35",
            day = "سوم",
            hour = "10",
            studentCount = "46",
            state = ExamStatus.CANCELLED
        ),
        ExamView(
            name = "ریاضی",
            collegeName = "مهندسی",
            className = "B35",
            day = "دوم",
            hour = "10",
            studentCount = "46",
            state = ExamStatus.IN_PROGRESS
        ),
        ExamView(
            name = "ریاضی",
            collegeName = "مهندسی",
            className = "B35",
            day = "صفرم",
            hour = "10",
            studentCount = "46",
            state = ExamStatus.NOT_STARTED
        ),
        ExamView(
            name = "ریاضی",
            collegeName = "مهندسی",
            className = "B35",
            day = "هفتم",
            hour = "10",
            studentCount = "46",
            state = ExamStatus.FINISHED
        ),
        ExamView(
            name = "ریاضی",
            collegeName = "دانشکده مهندسی6",
            className = "B35",
            day = "سه صفرم",
            hour = "6",
            studentCount = "46",
            state = ExamStatus.CANCELLED
        ),
        ExamView(
            name = "ریاضی",
            collegeName = "مهندسی",
            className = "B35",
            day = "سوم",
            hour = "5",
            studentCount = "46",
            state = ExamStatus.CANCELLED
        ),
        ExamView(
            name = "ریاضی",
            collegeName = "دانشکده مهندسی6",
            className = "B35",
            day = "سوم",
            hour = "10",
            studentCount = "46",
            state = ExamStatus.CANCELLED
        ),
        ExamView(
            name = "ریاضی",
            collegeName = "مهندسی",
            className = "B35",
            day = "سوم",
            hour = "12",
            studentCount = "46",
            state = ExamStatus.CANCELLED
        ),
        ExamView(
            name = "ریاضی",
            collegeName = "مهندسی",
            className = "B35",
            day = "سوم",
            hour = "10",
            studentCount = "46",
            state = ExamStatus.CANCELLED
        ),
        ExamView(
            name = "ریاضی",
            collegeName = "مهندسی",
            className = "B35",
            day = "سوم",
            hour = "11",
            studentCount = "46",
            state = ExamStatus.CANCELLED
        ),
        ExamView(
            name = "ریاضی",
            collegeName = "مهندسی",
            className = "B35",
            day = "سوم",
            hour = "10",
            studentCount = "46",
            state = ExamStatus.CANCELLED
        ),
        ExamView(
            name = "ریاضی",
            collegeName = "دانشکده مهندسی6",
            className = "B35",
            day = "سوم",
            hour = "7",
            studentCount = "46",
            state = ExamStatus.CANCELLED
        ),
        ExamView(
            name = "ریاضی",
            collegeName = "مهندسی",
            className = "B35",
            day = "سوم",
            hour = "10",
            studentCount = "46",
            state = ExamStatus.CANCELLED
        ),
    )
}