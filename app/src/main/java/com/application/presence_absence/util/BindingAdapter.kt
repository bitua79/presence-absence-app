package com.application.presence_absence.util

import android.net.Uri
import android.view.View
import androidx.databinding.BindingAdapter
import com.application.presence_absence.ui.examList.ExamView
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import java.util.*

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ShapeableImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        if (imageUrl.endsWith("svg")) {
            GlideToVectorYou
                .init()
                .with(view.context)
                .load(Uri.parse(imageUrl), view)
        } else {
            Glide.with(view.context)
                .load(imageUrl)
                .into(view)
        }
    }
}

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