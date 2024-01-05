package com.application.presence_absence.ui.utils

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import com.scottyab.rootbeer.RootBeer
import java.util.Locale

object DetectionUtils {

    fun isRooted(context: Context) = RootBeer(context).isRooted

    fun isEmulator(context: Context): Boolean {
        return (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                || "google_sdk" == Build.PRODUCT
                || (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).networkOperatorName.lowercase(
            Locale.getDefault()
        ) == "android"
                )
    }
}