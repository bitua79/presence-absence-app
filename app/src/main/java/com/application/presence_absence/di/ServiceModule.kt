package com.application.presence_absence.di

import com.application.presence_absence.data.remote.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {
    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): WebService =
        retrofit.create(WebService::class.java)

}