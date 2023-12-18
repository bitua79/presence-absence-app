package com.application.presence_absence.core.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

inline fun <T> Flow<T>.collectOnFragment(fragment: Fragment, crossinline onCollect: (T) -> Unit) {
    fragment.lifecycleScope.launchWhenStarted {
        this@collectOnFragment.collectLatest {
            onCollect(it)
        }
    }
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
