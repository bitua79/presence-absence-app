package com.application.presence_absence.core.utils

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import javax.inject.Inject


class FirebaseAnalyticsHelper @Inject constructor() {

    // Initialize variable
    private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    private fun logEvent(eventName: String, params: Map<String, String>) {
        firebaseAnalytics.logEvent(eventName) {
            for (i in params) {
                param(i.key, i.value)
            }
        }
    }

    private fun selectContent(itemCategory: String, itemId: String) {
        logEvent(
            FirebaseAnalytics.Event.SELECT_CONTENT,
            buildMap {
                put(FirebaseAnalytics.Param.ITEM_CATEGORY, itemCategory)
                put(FirebaseAnalytics.Param.ITEM_ID, itemId)
            }
        )
    }

    fun logLoginSelectContent(){
        selectContent("AUTH", "LOGIN")
    }

    fun logInvokeExamSelectContent(){
        selectContent("ATTENDANCE", "INVOKE_EXAM")
    }
}
