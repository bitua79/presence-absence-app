package com.application.presence_absence.ui.features.studentList.entities

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.application.presence_absence.R

class StudentAttendanceTagView private constructor(
    val status: StudentStatus,
    @DrawableRes
    val backgroundDrawable: Int,
    @ColorRes
    val foregroundColor: Int,
) {

    companion object {
        fun buildTagChip(status: StudentStatus): StudentAttendanceTagView {
            return when (status) {
                StudentStatus.PRESENCE -> StudentAttendanceTagView(
                    status,
                    backgroundDrawable = R.drawable.bg_student_presence,
                    foregroundColor = R.color.color_presence_foreground
                )

                StudentStatus.ABSENCE -> StudentAttendanceTagView(
                    status,
                    backgroundDrawable = R.drawable.bg_student_absence,
                    foregroundColor = R.color.color_absence_foreground
                )

                StudentStatus.NOT_SET -> StudentAttendanceTagView(
                    status,
                    backgroundDrawable = -1,
                    foregroundColor = -1
                )
            }
        }
    }
}