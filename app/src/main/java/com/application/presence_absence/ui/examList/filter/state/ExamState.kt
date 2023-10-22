package com.application.presence_absence.ui.examList.filter.state

import androidx.annotation.StringRes
import com.application.presence_absence.R

enum class ExamState(@StringRes val titleId: Int) {
    IN_PROGRESS(R.string.label_in_progress),
    NOT_STARTED(R.string.label_not_started),
    CANCELLED(R.string.label_cancelled),
    FINISHED(R.string.label_finished);
}