package com.application.presence_absence.domain.repository

import com.application.presence_absence.core.entities.Result
import com.application.presence_absence.domain.params.PostLogin

interface Repository {

    suspend fun login(param: PostLogin): Result<Unit>
}