package com.example.sui.feature.di

import android.content.Context
import com.example.sui.feature.data.datasource.SuiDatabase
import com.example.sui.feature.data.repository.SuiRepository
import com.example.sui.feature.data.repository.SuiRepositoryImpl
import com.example.sui.feature.domain.use_case.DetailUseCases
import com.example.sui.feature.domain.use_case.FilterGenealogyByFirstName
import com.example.sui.feature.domain.use_case.FilterGenealogyByGeneration
import com.example.sui.feature.domain.use_case.FilterGenealogyByNameAndGen
import com.example.sui.feature.domain.use_case.GenealogyUseCases
import com.example.sui.feature.domain.use_case.GetChildren
import com.example.sui.feature.domain.use_case.GetFather
import com.example.sui.feature.domain.use_case.GetFullGenealogy
import com.example.sui.feature.domain.use_case.GetMother
import com.example.sui.feature.domain.use_case.GetPersonById
import com.example.sui.feature.domain.use_case.GetSpouse
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
    fun provideGenealogyUseCases(repository: SuiRepository): GenealogyUseCases {
        return GenealogyUseCases(
            filterGenealogyByFirstName = FilterGenealogyByFirstName(repository),
            filterGenealogyByGeneration =  FilterGenealogyByGeneration(repository),
            filterGenealogyByNameAndGen = FilterGenealogyByNameAndGen(repository),
            getFullGenealogy = GetFullGenealogy(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDetailUseCases(repository: SuiRepository): DetailUseCases {
        return DetailUseCases(
            getPersonById = GetPersonById(repository),
            getFather = GetFather(repository),
            getMother = GetMother(repository),
            getSpouse = GetSpouse(repository),
            getChildren = GetChildren(repository)
        )
    }
}