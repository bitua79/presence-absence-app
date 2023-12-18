package com.application.presence_absence.data.repository

import com.application.presence_absence.core.entities.Result
import com.application.presence_absence.core.entities.Success
import com.application.presence_absence.core.utils.NetworkHandler
import com.application.presence_absence.core.utils.makeRequest
import com.application.presence_absence.data.remote.RemoteDataSource
import com.application.presence_absence.domain.params.PostLogin
import com.application.presence_absence.domain.repository.Repository
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val networkHandler: NetworkHandler
) : Repository {

    companion object {
        lateinit var token: String
    }

    override suspend fun login(param: PostLogin): Result<Unit> =
        makeRequest(networkHandler = networkHandler, action = {
            remoteDataSource.login(param.toPostLoginEntity())
        }) {
            // TODO: store in shared preferences if needed
            data.token?.let {
                token = it
            }
            Success(Unit)
        }
}