package com.application.presence_absence.core.utils

object UrlHelper {

    val BASE_URL = "http://10.0.2.2:8000/api/"

    val loginUrl = "${BASE_URL}auth/login"
    val examListUrl = "${BASE_URL}exams"
    fun getStudentListUrl(id: String) = "${BASE_URL}students/by_exam/$id"
    fun getSetStudentStatusUrl(examId: String, studentId: String) =
        "${BASE_URL}students/set_status/$studentId/$examId"

    fun String.localHostToEmulatorLocalHost(): String {
        return this.replace("localhost", "10.0.2.2")
    }

    fun getSetExamStatusUrl(examId: String) =
        "${BASE_URL}exams/set_status/$examId"
}
