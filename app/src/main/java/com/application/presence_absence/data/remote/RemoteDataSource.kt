package com.application.presence_absence.data.remote

import com.application.presence_absence.core.extensions.safeCall
import com.application.presence_absence.core.utils.UrlHelper
import com.application.presence_absence.core.utils.UrlHelper.getSetExamStatusUrl
import com.application.presence_absence.core.utils.UrlHelper.getSetStudentStatusUrl
import com.application.presence_absence.core.utils.UrlHelper.getStudentListUrl
import com.application.presence_absence.data.params.PostLoginEntity
import com.application.presence_absence.data.params.PostStatusEntity
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
        service.getAllStudents(getStudentListUrl(id))
    }

    suspend fun setStudentStatus(examId: String, studentId: String, param: PostStatusEntity) =
        safeCall {
            service.setStudentStatus(url = getSetStudentStatusUrl(examId, studentId), param = param)
        }

    suspend fun setExamStatus(examId: String, param: PostStatusEntity) =
        safeCall {
            service.setExamStatus(url = getSetExamStatusUrl(examId), param = param)
        }
}