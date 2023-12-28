package com.application.presence_absence.data.repository

import com.application.presence_absence.core.entities.Result
import com.application.presence_absence.core.entities.Success
import com.application.presence_absence.core.entities.map
import com.application.presence_absence.core.utils.NetworkHandler
import com.application.presence_absence.core.utils.TokenInterceptor
import com.application.presence_absence.core.utils.makeRequest
import com.application.presence_absence.data.remote.RemoteDataSource
import com.application.presence_absence.domain.entities.Exam
import com.application.presence_absence.domain.entities.Student
import com.application.presence_absence.domain.entities.toExam
import com.application.presence_absence.domain.entities.toStudent
import com.application.presence_absence.domain.params.PostLogin
import com.application.presence_absence.domain.params.PostStatus
import com.application.presence_absence.domain.repository.Repository
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val networkHandler: NetworkHandler
) : Repository {

    override suspend fun login(param: PostLogin): Result<Unit> =
        makeRequest(networkHandler = networkHandler, action = {
            remoteDataSource.login(param.toPostLoginEntity())
        }) {
            data.token?.let {
                TokenInterceptor().setToken(it)
            }
            Success(Unit)
        }

    override suspend fun getExamList(): Result<List<Exam>> =
        makeRequest(networkHandler = networkHandler, action = {
            remoteDataSource.getExamList()
        }) {
            this.map { list -> list.map { it.toExam() } }
        }

    override suspend fun getStudentList(id: String): Result<List<Student>> =
        makeRequest(networkHandler = networkHandler, action = {
            remoteDataSource.getStudentList(id)
        }) {
            this.map { list -> list.map { it.toStudent() } }
        }

    override suspend fun setStudentStatus(
        examId: String,
        studentId: String,
        param: PostStatus
    ): Result<Student> =
        makeRequest(networkHandler = networkHandler, action = {
            remoteDataSource.setStudentStatus(examId, studentId, param.toPostStatus())
        }) {
            this.map {
                it.toStudent()
            }
        }
}