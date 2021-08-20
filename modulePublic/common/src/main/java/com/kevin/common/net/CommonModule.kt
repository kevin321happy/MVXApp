package com.kevin.common.net

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * created by Kevin
 * on 2021/8/19
 * des:
 */
@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServer {
        return retrofit.create(ApiServer::class.java)
    }
}