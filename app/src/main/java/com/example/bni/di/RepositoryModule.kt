package com.example.bni.di

import com.example.bni.repository.IPortfolioRepository
import com.example.bni.repository.PortfolioRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePortfolioRepository(repository: PortfolioRepository): IPortfolioRepository

}