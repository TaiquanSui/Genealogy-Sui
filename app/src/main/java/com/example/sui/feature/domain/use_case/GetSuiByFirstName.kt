package com.example.sui.feature.domain.use_case

import com.example.sui.feature.data.repository.SuiRepository
import com.example.sui.feature.domain.model.Sui
import kotlinx.coroutines.flow.Flow

class GetSuiByFirstName (
    private val repository: SuiRepository
){
    suspend operator fun invoke(name: String): Flow<List<Sui>> {
        return repository.getSuiByFirstName(name)
    }
}