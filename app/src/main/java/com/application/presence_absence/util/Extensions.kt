package com.application.presence_absence.util

import android.os.Looper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


// Scopes
suspend fun runOnMain(block: () -> Unit) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        block()
    } else {
        withContext(Dispatchers.Main) {
            block()
        }
    }
}
