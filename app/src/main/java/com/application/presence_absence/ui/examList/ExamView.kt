package com.application.presence_absence.ui.examList

import android.os.Parcelable
import com.application.presence_absence.ui.examList.filter.state.ExamState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExamView(
    val name: String,
    val className: String,
    val collegeName: String,
    val day: String,
    val hour: String,
    val studentCount: String,
    val state: ExamState
) : Parcelable