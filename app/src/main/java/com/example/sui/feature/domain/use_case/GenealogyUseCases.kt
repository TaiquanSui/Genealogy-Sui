package com.example.sui.feature.domain.use_case

import com.example.sui.feature.data.repository.SuiRepository
import com.example.sui.feature.domain.model.Sui
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterGenealogyByFirstName @Inject constructor(
    private val repository: SuiRepository
) {
    operator fun invoke(name: String): Flow<List<Sui>> {
        return repository.getGenealogyByFirstName(name)
    }
}

class FilterGenealogyByGeneration @Inject constructor(
    private val repository: SuiRepository
) {
    operator fun invoke(generation: Int): Flow<List<Sui>> {
        return repository.getGenealogyByGeneration(generation)
    }
}

class FilterGenealogyByNameAndGen @Inject constructor(
    private val repository: SuiRepository
) {
    operator fun invoke(name: String, generation: Int): Flow<List<Sui>> {
        return repository.getGenealogyByNameAndGeneration(name, generation)
    }
}

class GetFullGenealogy @Inject constructor(
    private val repository: SuiRepository
) {
    operator fun invoke(): Flow<List<Sui>> {
        return repository.getAllGenealogy()
    }
}

data class GenealogyUseCases (
    val filterGenealogyByFirstName: FilterGenealogyByFirstName,
    val filterGenealogyByGeneration: FilterGenealogyByGeneration,
    val filterGenealogyByNameAndGen: FilterGenealogyByNameAndGen,
    val getFullGenealogy: GetFullGenealogy
)