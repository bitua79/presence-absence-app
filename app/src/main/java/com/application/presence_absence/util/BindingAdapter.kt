package com.application.presence_absence.util

import android.view.View
import androidx.databinding.BindingAdapter
import com.application.presence_absence.ui.examList.ExamView
import com.google.android.material.textview.MaterialTextView
import java.util.*

@BindingAdapter("examClass")
fun bindExamClass(view: MaterialTextView, examEntity: ExamView) {
    // TODO: Use string resource instead
    view.text = "${examEntity.className} - ${examEntity.name}"
}

@BindingAdapter("examDate")
fun bindExamDate(view: MaterialTextView, examEntity: ExamView) {
    // TODO: Use string resource instead
    view.text = "${examEntity.day} - ${examEntity.hour}"
}

@BindingAdapter("isVisibleOrGone")
fun bindIsVisibleOrGone(view: View, visible: Boolean) {
    view.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}