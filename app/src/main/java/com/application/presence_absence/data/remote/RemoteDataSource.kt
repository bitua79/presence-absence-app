package com.application.presence_absence.data.remote

import com.application.presence_absence.core.utils.UrlHelper
import com.application.presence_absence.core.utils.UrlHelper.getStudentListUrl
import com.application.presence_absence.core.utils.safeCall
import com.application.presence_absence.data.params.PostLoginEntity
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val service: WebService
) {
    suspend fun login(param: PostLoginEntity) = safeCall {
        service.doLogin(UrlHelper.loginUrl, param)
    }

    suspend fun getExamList() = safeCall {
        service.getAllExams(UrlHelper.examListUrl)

    }

    suspend fun getStudentList(id: String) = safeCall {
        val x = service.getAllStudents(getStudentListUrl(id))
        x
    }
}