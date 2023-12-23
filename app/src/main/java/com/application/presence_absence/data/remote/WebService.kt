package com.application.presence_absence.data.remote

import com.application.presence_absence.core.entities.Resource
import com.application.presence_absence.data.entities.ExamEntity
import com.application.presence_absence.data.entities.LoginEntity
import com.application.presence_absence.data.params.PostLoginEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {
    @POST("auth/login")
    suspend fun doLogin(
        @Body param: PostLoginEntity
    ): Response<Resource<LoginEntity>>

    @GET("exams")
    suspend fun getAllExams(): Response<Resource<List<ExamEntity>>>

}