package com.example.bni.di

import com.example.bni.repository.IPromoRepository
import com.example.bni.repository.PromoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [ServiceModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePromoRepository(repository: PromoRepository): IPromoRepository

}