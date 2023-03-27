package com.application.presence_absence.ui.examList

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExamView(
    val name: String,
    val className: String,
    val collegeName: String,
    val day: String,
    val hour: String,
    val studentCount: String
) : Parcelable