package com.application.presence_absence.ui.features.examList.entities

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.application.presence_absence.R

class ExamStatusTagView private constructor(
    val stateType: ExamStatus,
    @DrawableRes
    val backgroundDrawable: Int,
    @ColorRes
    val textColor: Int,
) {

    companion object {
        fun buildTagChip(chipType: ExamStatus): ExamStatusTagView {
            return when (chipType) {
                ExamStatus.IN_PROGRESS -> ExamStatusTagView(
                    chipType,
                    backgroundDrawable = R.drawable.bg_exam_state_in_progress,
                    textColor = R.color.color_in_progress_exam_text
                )

                ExamStatus.NOT_STARTED -> ExamStatusTagView(
                    chipType,
                    backgroundDrawable = R.drawable.bg_exam_state_not_started,
                    textColor = R.color.color_not_started_exam_text
                )

                ExamStatus.CANCELLED -> ExamStatusTagView(
                    chipType,
                    backgroundDrawable = R.drawable.bg_exam_state_cancelled,
                    textColor = R.color.color_cancelled_exam_text
                )

                ExamStatus.FINISHED -> ExamStatusTagView(
                    chipType,
                    backgroundDrawable = R.drawable.bg_exam_state_finished,
                    textColor = R.color.color_finished_exam_text
                )
            }
        }
    }
}