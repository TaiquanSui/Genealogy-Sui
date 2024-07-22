package com.example.sui.feature.domain.use_case

import com.example.sui.feature.data.repository.SuiRepository
import com.example.sui.feature.domain.model.Sui
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPersonById @Inject constructor(
    private val repository: SuiRepository
) {
    suspend operator fun invoke(id: String): Sui? {
        return repository.getPersonById(id)
    }
}

class GetChildren @Inject constructor(
    private val repository: SuiRepository
) {
    suspend operator fun invoke(id: String): Flow<List<Sui>> {
        return repository.getChildren(id)
    }
}

class GetSpouse @Inject constructor(
    private val repository: SuiRepository
) {
    suspend operator fun invoke(id: String): Flow<List<Sui>> {
        return repository.getSpouse(id)
    }
}

class GetFather @Inject constructor(
    private val repository: SuiRepository
) {
    suspend operator fun invoke(id: String): Sui? {
        return repository.getFather(id)
    }
}

class GetMother @Inject constructor(
    private val repository: SuiRepository
) {
    suspend operator fun invoke(id: String): Sui? {
        return repository.getMother(id)
    }
}

data class DetailUseCases (
    val getPersonById: GetPersonById,
    val getChildren: GetChildren,
    val getSpouse: GetSpouse,
    val getFather: GetFather,
    val getMother: GetMother
)