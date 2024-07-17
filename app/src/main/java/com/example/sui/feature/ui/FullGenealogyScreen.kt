package com.example.sui.feature.ui


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import com.example.sui.feature.domain.model.Sui


@Composable
fun FullGenealogyScreen(
    fullGenealogy : List<Sui>
){
    Text(text = "here is full tree")
//    LazyColumn {
//        items(fullGenealogy){ item: Sui ->
//            SuiRow(item)
//        }
//    }
//

}

@Composable
fun SuiRow(sui: Sui) {
    TODO("Not yet implemented")
}
