package com.application.presence_absence.domain.params

import com.application.presence_absence.data.params.PostStatusEntity

class PostStatus(val status: Int) {
    fun toPostStatus() = PostStatusEntity(status = status)
}

