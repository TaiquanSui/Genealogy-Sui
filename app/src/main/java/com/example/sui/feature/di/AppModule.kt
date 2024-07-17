package com.example.sui.feature.di

import android.content.Context
import com.example.sui.feature.data.datasource.SuiDatabase
import com.example.sui.feature.data.repository.SuiRepository
import com.example.sui.feature.data.repository.SuiRepositoryImpl
import com.example.sui.feature.domain.use_case.GetAllSui
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSuiDatabase(@ApplicationContext context: Context): SuiDatabase {
        return SuiDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideSuiRepository(db: SuiDatabase): SuiRepository{
        return SuiRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideGetAllSuiUseCase(repository: SuiRepository): GetAllSui {
        return GetAllSui(repository)
    }
}