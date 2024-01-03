package com.application.presence_absence.domain.repository

import com.application.presence_absence.core.entities.Result
import com.application.presence_absence.domain.entities.Exam
import com.application.presence_absence.domain.entities.Student
import com.application.presence_absence.domain.params.PostLogin
import com.application.presence_absence.domain.params.PostStatus

interface Repository {

    suspend fun login(param: PostLogin): Result<Unit>
    suspend fun getExamList(): Result<List<Exam>>
    suspend fun getStudentList(id: String): Result<List<Student>>

    suspend fun setStudentStatus(
        examId: String,
        studentId: String,
        param: PostStatus
    ): Result<Student>


    suspend fun setExamStatus(
        examId: String,
        param: PostStatus
    ): Result<Exam>
}