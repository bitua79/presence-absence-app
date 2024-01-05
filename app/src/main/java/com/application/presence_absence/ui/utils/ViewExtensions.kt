package com.application.presence_absence.ui.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.application.presence_absence.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import saman.zamani.persiandate.PersianDate
import kotlin.system.exitProcess


fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

// Set dialog setting
fun BottomSheetDialog.createDialog(): BottomSheetDialog {
    setOnShowListener {
        val dialog = it as BottomSheetDialog
        val behaviour = dialog.behavior

        val bottomSheet =
            dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        if (bottomSheet != null) {
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED

            // Tod disable dialog from dragging by user
            behaviour.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
        }

    }
    return this
}

fun Fragment.createSnackbar(@StringRes message: Int, @StringRes actionText: Int): Snackbar {
    val snackbar = Snackbar.make(
        requireView(),
        getString(message),
        Snackbar.LENGTH_INDEFINITE
    )

    with(snackbar) {
        setAction(getString(actionText)) {
            exitProcess(1)
        }
        setBackgroundTint(getColor(R.color.color_surface))
        setTextColor(getColor(R.color.color_on_surface))

        setActionTextColor(getColor(R.color.color_primary))
        animationMode = Snackbar.ANIMATION_MODE_FADE
    }

    return snackbar
}

fun Fragment.hideKeyboard() {
    val imm =
        requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = requireActivity().currentFocus
    if (view == null) {
        view = View(requireActivity())
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun PersianDate.getShDate(): String {
    return "${dayName()} $shMonth/$shDay"
}

fun Fragment.getColor(@ColorRes colorId: Int): Int {
    return ContextCompat.getColor(requireContext(), colorId)
}