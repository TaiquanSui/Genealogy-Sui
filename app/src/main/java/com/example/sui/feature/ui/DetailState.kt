package com.example.sui.feature.ui

import com.example.sui.feature.domain.model.Sui

data class DetailState (
    val selected: Sui? = null,
    val father: Sui? = null,
    val mother: Sui? = null,
    val spouse: List<Sui>? = null,
    val children: List<Sui>? = null,
    val daughter: List<Sui>? = null
)