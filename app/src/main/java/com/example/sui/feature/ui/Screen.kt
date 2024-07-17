package com.example.sui.feature.ui

sealed class Screen(val route: String){
    data object MainScreen : Screen("main_screen")
    data object FullGenealogyScreen : Screen("full_genealogy_screen")
}
