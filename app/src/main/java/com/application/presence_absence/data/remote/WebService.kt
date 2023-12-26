package com.application.presence_absence.data.remote

import com.application.presence_absence.core.entities.Resource
import com.application.presence_absence.data.entities.ExamEntity
import com.application.presence_absence.data.entities.LoginEntity
import com.application.presence_absence.data.entities.StudentEntity
import com.application.presence_absence.data.params.PostLoginEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface WebService {
    @POST
    suspend fun doLogin(
        @Url url: String,
        @Body param: PostLoginEntity
    ): Response<Resource<LoginEntity>>

    @GET
    suspend fun getAllExams(@Url url: String): Response<Resource<List<ExamEntity>>>

    @GET
    suspend fun getAllStudents(@Url url: String): Response<Resource<List<StudentEntity>>>

}