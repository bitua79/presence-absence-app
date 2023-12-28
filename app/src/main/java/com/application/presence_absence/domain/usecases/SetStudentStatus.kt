package com.application.presence_absence.domain.usecases

import com.application.presence_absence.core.entities.Result
import com.application.presence_absence.core.extensions.runIO
import com.application.presence_absence.domain.entities.Student
import com.application.presence_absence.domain.params.PostStatus
import com.application.presence_absence.domain.repository.Repository
import javax.inject.Inject


class SetStudentStatus @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(
        examId: String,
        studentId: String,
        param: PostStatus
    ): Result<Student> {
        return runIO {
            repository.setStudentStatus(examId, studentId, param)
        }
    }
}