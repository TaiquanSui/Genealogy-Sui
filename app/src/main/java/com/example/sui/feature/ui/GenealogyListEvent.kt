package com.example.sui.feature.ui

sealed class GenealogyListEvent {
    data class filter(val name: String, val generation: Int): GenealogyListEvent()
}