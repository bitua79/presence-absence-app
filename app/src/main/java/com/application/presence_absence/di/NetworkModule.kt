package com.application.presence_absence.di

import com.application.presence_absence.core.utils.TokenInterceptor
import com.application.presence_absence.core.utils.UrlHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Suppress("unused")
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    // Okhttp
    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addNetworkInterceptor { chain ->
                var request = chain.request()

                val url = request.url.newBuilder().build()

                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }

        client.addNetworkInterceptor(logging)
        client.addInterceptor(TokenInterceptor())
        return client.build()
    }

    // Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(UrlHelper.BASE_URL).client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()

}