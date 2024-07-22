package com.example.sui.feature.data.repository

import com.example.sui.feature.domain.model.Sui
import kotlinx.coroutines.flow.Flow

interface SuiRepository {
    fun getGenealogyByNameAndGeneration(name: String, generation: Int): Flow<List<Sui>>
    fun getGenealogyByFirstName(name: String): Flow<List<Sui>>
    fun getGenealogyByGeneration(generation: Int): Flow<List<Sui>>
    fun getAllGenealogy(): Flow<List<Sui>>

    suspend fun getPersonById(id: String): Sui?
    suspend fun getChildren(id: String): Flow<List<Sui>>
    suspend fun getSpouse(id: String): Flow<List<Sui>>
    suspend fun getFather(id: String): Sui?
    suspend fun getMother(id: String): Sui?
}