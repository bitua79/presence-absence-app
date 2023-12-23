package com.application.presence_absence.domain.usecases

import com.application.presence_absence.core.entities.Result
import com.application.presence_absence.domain.params.PostLogin
import com.application.presence_absence.domain.repository.Repository
import com.application.presence_absence.ui.utils.runIO
import javax.inject.Inject


class DoLogin @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(params: PostLogin): Result<Unit> {
        return runIO {
            repository.login(params)
        }
    }
}