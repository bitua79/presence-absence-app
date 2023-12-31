package com.application.presence_absence.ui.utils

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.application.presence_absence.ui.features.examList.entities.ExamView
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

@BindingAdapter("setIntAsText")
fun setIntAsText(view: MaterialTextView, text: Int) {
    view.text = text.toString()
}

@BindingAdapter("setExamDay")
fun bindExamDay(view: MaterialTextView, examEntity: ExamView) {
    view.text = "${examEntity.day.title}، ${examEntity.dateView}"
}

@BindingAdapter("setExamHour")
fun bindExamHour(view: MaterialTextView, hour: Int) {
    view.text = "ساعت ${hour}"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setExamTime")
fun bindExamTime(view: MaterialTextView, examEntity: ExamView) {
    view.text = "${examEntity.day.title}، ساعت ${examEntity.hour}"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setExamPlace")
fun bindExamPlace(view: MaterialTextView, examEntity: ExamView) {
    view.text = "کلاس ${examEntity.className}"
}

@BindingAdapter("isVisibleOrGone")
fun bindIsVisibleOrGone(view: View, visible: Boolean) {
    view.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("setBackgroundDrawableById")
fun setBackgroundDrawableById(view: View, @DrawableRes bgId: Int) {
    if (bgId == -1) return
    with(view) {
        background = ContextCompat.getDrawable(context, bgId)
    }
}

@BindingAdapter("setTextColorById")
fun setTextColorById(textView: TextView, @ColorRes colorId: Int) {
    if (colorId == -1) return
    with(textView) {
        setTextColor(ContextCompat.getColor(context, colorId))
    }
}

@BindingAdapter("setTextById")
fun setTextById(textView: TextView, @StringRes stringId: Int) {
    if (stringId == -1) return
    with(textView) {
        textView.text = context.getString(stringId)
    }
}

@BindingAdapter("setTintById")
fun setTintById(image: ShapeableImageView, @ColorRes colorId: Int) {
    if (colorId == -1) return
    with(image) {
        val tintColor = ContextCompat.getColor(context, colorId)
        setColorFilter(tintColor, PorterDuff.Mode.SRC_IN)
    }
}