package com.example.sui.feature.domain.use_case

import com.example.sui.feature.data.repository.SuiRepository
import com.example.sui.feature.domain.model.Sui
import kotlinx.coroutines.flow.Flow

class GetAllSui (
    private val repository: SuiRepository
){
    operator fun invoke(): Flow<List<Sui>> {
        return repository.getAllSui()
    }
}