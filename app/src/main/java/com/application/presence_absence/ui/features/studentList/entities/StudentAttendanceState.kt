package com.application.presence_absence.ui.features.studentList.entities

import androidx.annotation.StringRes
import com.application.presence_absence.R

enum class StudentAttendanceState(@StringRes val titleId: Int) {
    PRESENCE(R.string.label_present),
    ABSENCE(R.string.label_absent),
    NOT_SET(-1)
}