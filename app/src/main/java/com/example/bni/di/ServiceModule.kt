package com.example.bni.di

import com.example.bni.network.service.PromoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun providesPromoApi(retrofit: Retrofit): PromoApi {
        return retrofit.create(PromoApi::class.java)
    }

}