package com.example.sui.feature.data.repository

import com.example.sui.feature.domain.model.Sui
import kotlinx.coroutines.flow.Flow

interface SuiRepository {
    suspend fun getSuiByFirstName(name: String): Flow<List<Sui>>
    fun getAllSui(): Flow<List<Sui>>
}