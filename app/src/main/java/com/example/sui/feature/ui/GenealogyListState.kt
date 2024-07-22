package com.example.sui.feature.ui

import com.example.sui.feature.domain.model.Sui

data class GenealogyListState(
    val genealogyList: List<Sui>? = emptyList(),
    val nameFilter: String? = null,
    val generationFilter: Int? = null
)