package com.application.presence_absence.core.extensions

import android.os.Looper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun runOnMain(block: () -> Unit) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        block()
    } else {
        withContext(Dispatchers.Main) {
            block()
        }
    }
}
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