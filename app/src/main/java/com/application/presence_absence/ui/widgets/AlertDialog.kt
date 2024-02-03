package com.application.presence_absence.ui.widgets

import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.application.presence_absence.R
import com.application.presence_absence.databinding.AlertDialogBinding

class AlertDialog(
    val title: String,
    private val description: String,
    private val buttonText: String,
    val onOkClick: () -> Unit
) : DialogFragment(R.layout.alert_dialog) {

    private lateinit var binding: AlertDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AlertDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val width = (displayMetrics.widthPixels * 0.90).toInt()
        dialog?.window?.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(TRANSPARENT))
    }

    private fun initView() {
        binding.title = title
        binding.description = description
        binding.buttonText = buttonText

        binding.btnAction.setOnClickListener {
            onOkClick()
            dismiss()
        }
    }
}