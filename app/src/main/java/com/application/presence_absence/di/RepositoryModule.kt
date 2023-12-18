package com.application.presence_absence.di

import com.application.presence_absence.data.repository.DefaultRepository
import com.application.presence_absence.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsAppRepository(repos: DefaultRepository): Repository

}