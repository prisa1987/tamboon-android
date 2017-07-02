package com.omise.tamboon.presentation.charities.di

import com.omise.tamboon.data.repository.CharityRepository
import com.omise.tamboon.data.repository.CharityRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class CharityModule {

    @Provides
    fun provideCharityRepository(): CharityRepository = CharityRepositoryImpl()

}
