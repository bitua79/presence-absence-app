package com.application.presence_absence.core.extensions

import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


suspend fun <T> runIO(block: suspend () -> T): T {
    return withContext(Dispatchers.IO) {
        block()
    }
}

suspend fun <T> runMain(block: suspend () -> T): T {
    return if (Looper.getMainLooper().thread == Looper.myLooper()?.thread) {
        block()
    } else {
        withContext(Dispatchers.Main) {
            block()
        }
    }
}

fun ViewModel.launchIO(block: suspend () -> Unit) {
    viewModelScope.launch(Dispatchers.IO) { block() }
}


suspend fun runOnMain(block: () -> Unit) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        block()
    } else {
        withContext(Dispatchers.Main) {
            block()
        }
    }
}