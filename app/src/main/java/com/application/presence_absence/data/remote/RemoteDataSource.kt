package com.application.presence_absence.data.remote

import com.application.presence_absence.core.utils.UrlHelper
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
        val c = service.getAllExams(UrlHelper.examListUrl)
        c
    }
}