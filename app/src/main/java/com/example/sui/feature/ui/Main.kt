package com.example.sui.feature.ui


import android.content.res.Resources.Theme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sui.R
import com.example.sui.feature.domain.model.Sui
import com.example.sui.feature.vm.GenealogyViewModel

@Composable
fun Main(
    navController: NavController,
    viewModel: GenealogyViewModel
) {

    Scaffold (
        modifier = Modifier.systemBarsPadding()
    ){ innerPadding->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp,30.dp,10.dp,0.dp)
                .fillMaxSize()
        ) {
            Title()
            Genealogy(modifier = Modifier
                .weight(1f)
                .padding(top = 20.dp),
                viewModel,
                navController)
            FilterPanel(viewModel)
        }

        if (viewModel.bottomSheetState.value) {
            BottomSheet(viewModel)
        }
    }


}

@Composable
private fun Title() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.sui),
            contentDescription = "sui",
            Modifier.size(50.dp, 50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.shi),
            contentDescription = "shi",
            Modifier.size(50.dp, 50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.zu),
            contentDescription = "zu",
            Modifier.size(50.dp, 50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.pu),
            contentDescription = "pu",
            Modifier.size(50.dp, 50.dp)
        )
    }
}

@Composable
private fun Genealogy(
    modifier: Modifier,
    viewModel: GenealogyViewModel,
    navController: NavController
) {
    val list = viewModel.state.value.genealogyList

    if (list.isNullOrEmpty()) {
        Text(
            text = "没有合适的结果",
            modifier
        )
    } else {
        ItemsView(list, modifier, navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemsView(list: List<Sui>, modifier:Modifier, navController: NavController) {

    LazyColumn(
        modifier
    ) {

        stickyHeader {
            Column(
                modifier = Modifier.background(
                    MaterialTheme.colorScheme.background
                )
            ) {
                Row (
                    Modifier.padding(16.dp)
                ){
                    Text(
                        text = "世代",
                        modifier = Modifier
                            .weight(1F),
                        textAlign = TextAlign.Left
                    )
                    Text(
                        text = "名字",
                        modifier = Modifier
                            .weight(4F),
                        textAlign = TextAlign.Left
                    )
                }
            }
        }

        items(list) { item ->
            Row(
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.DetailScreen.withArgs(item.id))
                    }
                    .padding(16.dp)
            ) {
                Text(
                    text = item.generation.toString(),
                    modifier = Modifier.weight(1F),
                    textAlign = TextAlign.Left
                )
                Text(
                    text = item.firstName,
                    modifier = Modifier.weight(4F),
                    textAlign = TextAlign.Left
                )
            }
        }
    }
}

@Composable
fun FilterPanel(viewModel: GenealogyViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        val state = viewModel.state.value
        Text(text = "当前过滤器：")
        Text(text = "名字: ${state.nameFilter ?: "无"}")
        Text(text = "世代: ${state.generationFilter ?: "无"}")

        OutlinedButton(
            onClick = { viewModel.bottomSheet() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 20.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(text = "过滤", color = Color.Black)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun BottomSheet(viewModel: GenealogyViewModel) {
    ModalBottomSheet(
        onDismissRequest = { viewModel.bottomSheet() }
    ) {
        FilterBottomSheet(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            viewModel
        )
    }
}

@Composable
fun FilterBottomSheet(
    modifier: Modifier,
    viewModel: GenealogyViewModel
) {
    var nameFilterText by remember { mutableStateOf(viewModel.state.value.nameFilter ?: "") }
    var generationFilterText by remember { mutableStateOf(viewModel.state.value.generationFilter?.toString() ?: "") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp,5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "按名字：",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            OutlinedTextField(
                value = nameFilterText,
                onValueChange = { nameFilterText = it },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "按世代：",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            OutlinedTextField(
                value = generationFilterText,
                onValueChange = { generationFilterText = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = {
                viewModel.nameChanged(nameFilterText.ifEmpty { null })
                viewModel.generationChanged(generationFilterText.toIntOrNull())
                viewModel.bottomSheet()
                viewModel.getGenealogyList()
            },
            modifier = Modifier.align(Alignment.End),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(text = "查询", color = Color.Black)
        }
    }
}
