package com.application.presence_absence.ui.studentList

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.application.presence_absence.R

class StudentAttendanceTagView private constructor(
    val status: StudentAttendance,
    @DrawableRes
    val backgroundDrawable: Int,
    @ColorRes
    val foregroundColor: Int,
) {

    companion object {
        fun buildTagChip(status: StudentAttendance): StudentAttendanceTagView {
            return when (status) {
                StudentAttendance.PRESENCE -> StudentAttendanceTagView(
                    status,
                    backgroundDrawable = R.drawable.bg_student_presence,
                    foregroundColor = R.color.color_presence_foreground
                )

                StudentAttendance.ABSENCE -> StudentAttendanceTagView(
                    status,
                    backgroundDrawable = R.drawable.bg_student_absence,
                    foregroundColor = R.color.color_absence_foreground
                )

                StudentAttendance.NOT_SET -> StudentAttendanceTagView(
                    status,
                    backgroundDrawable = -1,
                    foregroundColor = -1
                )
            }
        }
    }
}