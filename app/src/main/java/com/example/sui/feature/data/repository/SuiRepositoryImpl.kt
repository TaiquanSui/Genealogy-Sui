package com.example.sui.feature.data.repository

import com.example.sui.feature.data.datasource.SuiDao
import com.example.sui.feature.domain.model.Sui
import kotlinx.coroutines.flow.Flow

class SuiRepositoryImpl (
    private val dao: SuiDao
): SuiRepository {
    override suspend fun getSuiByFirstName(name: String): Flow<List<Sui>> {
       return dao.getSuiByFirstName(name)
    }

    override fun getAllSui(): Flow<List<Sui>> {
        return dao.getAllSui()
    }

}