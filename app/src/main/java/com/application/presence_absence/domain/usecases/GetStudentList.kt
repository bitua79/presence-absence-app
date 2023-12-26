package com.application.presence_absence.domain.usecases

import com.application.presence_absence.core.entities.Result
import com.application.presence_absence.domain.entities.Student
import com.application.presence_absence.domain.repository.Repository
import com.application.presence_absence.ui.utils.runIO
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