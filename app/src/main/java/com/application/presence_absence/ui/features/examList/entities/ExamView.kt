package com.application.presence_absence.ui.features.examList.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExamView(
    val name: String,
    val className: String,
    val collegeName: String,
    val day: Int,
    val hour: String,
    val studentCount: String,
    val state: ExamStatus
) : Parcelable