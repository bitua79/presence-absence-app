package com.application.presence_absence.core.extensions

import android.os.Looper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun <T> runMain(block: suspend () -> T): T {
    return if (Looper.getMainLooper().thread == Looper.myLooper()?.thread) {
        block()
    } else {
        withContext(Dispatchers.Main) {
            block()
        }
    }
}