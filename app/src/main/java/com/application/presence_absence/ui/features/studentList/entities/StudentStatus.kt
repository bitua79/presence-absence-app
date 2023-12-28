package com.application.presence_absence.ui.features.studentList.entities

import androidx.annotation.StringRes
import com.application.presence_absence.R

enum class StudentStatus(@StringRes val titleId: Int, val numValue: Int) {
    PRESENCE(R.string.label_present, 1),
    ABSENCE(R.string.label_absent, -1),
    NOT_SET(-1, 0)
}

fun findStudentStatusByNumValue(value: Int): StudentStatus? {
    StudentStatus.values().forEach {
        if (value == it.numValue) {
            return it
        }
    }
    return null
}