package com.application.presence_absence.domain.params

import com.application.presence_absence.data.params.PostLoginEntity

data class PostLogin(
    val username: String,
    val password: String
) {

    fun toPostLoginEntity() = PostLoginEntity(
        username = username,
        password = password
    )
}