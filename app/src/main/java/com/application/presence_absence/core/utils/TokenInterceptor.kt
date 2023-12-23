package com.application.presence_absence.core.utils

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class TokenInterceptor : Interceptor {

    companion object {
        private var token: String? = null
    }

    fun setToken(token: String?) {
        TokenInterceptor.token = token
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        //rewrite the request to add bearer token
        if (!token.isNullOrBlank())
            requestBuilder.header("Authorization", "Bearer $token")


        return chain.proceed(requestBuilder.build())
    }
}