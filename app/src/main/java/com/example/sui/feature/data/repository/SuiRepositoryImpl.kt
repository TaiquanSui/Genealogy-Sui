package com.example.sui.feature.data.repository

import com.example.sui.feature.data.datasource.SuiDao
import com.example.sui.feature.domain.model.Sui
import kotlinx.coroutines.flow.Flow

class SuiRepositoryImpl (
    private val dao: SuiDao
): SuiRepository {
    override fun getGenealogyByNameAndGeneration(name: String, generation: Int): Flow<List<Sui>> {
        return dao.getGenealogyByNameAndGeneration(name, generation)
    }

    override fun getGenealogyByFirstName(name: String): Flow<List<Sui>> {
        return dao.getGenealogyByFirstName(name)
    }

    override fun getGenealogyByGeneration(generation: Int): Flow<List<Sui>> {
        return dao.getGenealogyByGeneration(generation)
    }

    override fun getAllGenealogy(): Flow<List<Sui>> {
        return dao.getAllGenealogy()
    }

    override suspend fun getPersonById(id: String): Sui? {
        return dao.getSuiById(id)
    }

    override suspend fun getChildren(id: String): Flow<List<Sui>> {
        return dao.getChildren(id)
    }

    override suspend fun getSpouse(id: String): Flow<List<Sui>> {
        return dao.getSpouse(id)
    }

    override suspend fun getFather(id: String): Sui? {
        return dao.getFather(id)
    }

    override suspend fun getMother(id: String): Sui? {
        return dao.getMother(id)
    }

}