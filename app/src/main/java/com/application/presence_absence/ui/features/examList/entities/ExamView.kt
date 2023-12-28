package com.application.presence_absence.ui.features.examList.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExamView(
    val id: Int,
    val name: String,
    val className: String,
    val faculty: String,
    val day: ExamDay,
    val dateView: String,
    val hour: Int,
    val state: ExamStatus
) : Parcelable