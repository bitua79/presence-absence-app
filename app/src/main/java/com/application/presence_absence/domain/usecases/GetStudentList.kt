package com.application.presence_absence.domain.usecases

import com.application.presence_absence.core.entities.Result
import com.application.presence_absence.core.extensions.runIO
import com.application.presence_absence.domain.entities.Student
import com.application.presence_absence.domain.repository.Repository
import javax.inject.Inject


class GetStudentList @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id: String): Result<List<Student>> {
        return runIO {
            repository.getStudentList(id)
        }
    }
}