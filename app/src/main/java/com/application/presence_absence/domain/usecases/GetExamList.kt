package com.application.presence_absence.domain.usecases

import com.application.presence_absence.core.entities.Result
import com.application.presence_absence.core.extensions.runIO
import com.application.presence_absence.domain.entities.Exam
import com.application.presence_absence.domain.repository.Repository
import javax.inject.Inject


class GetExamList @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Result<List<Exam>> {
        return runIO {
            repository.getExamList()
        }
    }
}