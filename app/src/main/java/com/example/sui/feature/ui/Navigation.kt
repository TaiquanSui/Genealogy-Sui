package com.example.sui.feature.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sui.feature.vm.DetailViewModel
import com.example.sui.feature.vm.GenealogyViewModel


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            val viewModel: GenealogyViewModel = hiltViewModel()
            Main(navController,viewModel)
        }

        composable(
            route = Screen.DetailScreen.route+"/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val viewModel: DetailViewModel = hiltViewModel()
            if (id != null) {
                Detail(navController = navController, viewModel, id = id)
            }
        }

    }
}

//@Composable
//inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
//    val navGraphRoute = destination.parent?.route ?: return viewModel()
//    val parentEntry = remember(this) {
//        navController.getBackStackEntry(navGraphRoute)
//    }
//    return viewModel(parentEntry)
//}