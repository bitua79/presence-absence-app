package com.application.presence_absence.core.utils

object UrlHelper{

    val BASE_URL = "http://10.0.2.2:8000/api/"

    val loginUrl = "${BASE_URL}auth/login"
    val examListUrl = "${BASE_URL}exams"
}
