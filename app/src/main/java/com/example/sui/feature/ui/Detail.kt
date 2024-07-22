package com.example.sui.feature.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sui.R

import com.example.sui.feature.domain.model.Sui
import com.example.sui.feature.vm.DetailViewModel

@Composable
fun Detail(
    navController: NavController,
    viewModel: DetailViewModel,
    id: String
) {
    LaunchedEffect(id) {
        viewModel.getInfoById(id)
    }

    val selectedTab = remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                Modifier.background(Color.White)
            ) {
                NavigationBarItem(
                    selected = selectedTab.value == 0,
                    onClick = { selectedTab.value = 0 },
                    label = { Text("详细信息") },
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.detail),
                            contentDescription = "详细信息",
                            modifier = Modifier.size(33.dp)
                        )
                    },
                    modifier = Modifier.padding(5.dp)
                )
                NavigationBarItem(
                    selected = selectedTab.value == 1,
                    onClick = { selectedTab.value = 1 },
                    label = { Text("关系图") },
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.hierarchy),
                            contentDescription = "关系图",
                            modifier = Modifier.size(33.dp)
                        )
                    },
                    modifier = Modifier.padding(5.dp)
                )
            }
        },
        modifier = Modifier.systemBarsPadding()
    ) { innerPadding ->
        if (selectedTab.value == 0) {
            DetailPage(viewModel, navController, Modifier.padding(innerPadding))
        } else {
            RelationshipGraph(viewModel, Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun DetailPage(viewModel: DetailViewModel, navController: NavController, modifier: Modifier) {
    val sui = viewModel.state.value.selected
    if (sui == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .padding(5.dp,20.dp,5.dp,0.dp)
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", modifier = Modifier.size(24.dp))
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "返回",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(55.dp,20.dp) // 调整 padding 值
            ) {
                Text(text = sui.firstName, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(16.dp))
                BodyText("世代: ${sui.generation}")
                BodyText("世代: ${sui.generation}")
                BodyText("排行: ${sui.rank}")
                BodyText("居住地: ${sui.residencePlace}")
                BodyText("生辰(公历): ${sui.birthday}")
                BodyText("卒殁标识: ${sui.noteOfDeath}")
                BodyText("殁辰(公历): ${sui.death}")
                BodyText("葬地: ${sui.burialPlace}")
                BodyText("字号别称: ${sui.alias}")
                BodyText("简介: ${sui.introduction}")
                BodyText("职业: ${sui.profession}")
                BodyText("个人信息: ${sui.personalInformation}")
                BodyText("小注: ${sui.note}")
            }
        }
    }
}

@Composable
fun BodyText(text: String) {
    Text(text = text, style = MaterialTheme.typography.titleMedium)
}




@Composable
fun RelationshipGraph(viewModel: DetailViewModel, modifier: Modifier) {
    val state = viewModel.state.value
    val sui = state.selected

    val horizontalScrollState = rememberScrollState()
    val verticalScrollState = rememberScrollState()

    // 每次 sui 变化时将 scroll 状态重置
    LaunchedEffect(sui) {
        horizontalScrollState.scrollTo(0)
        verticalScrollState.scrollTo(0)
    }

    if (sui == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .horizontalScroll(horizontalScrollState)
                .verticalScroll(verticalScrollState),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ParentRow(state)
                ParentToSuiLine()
                SuiRow(state)

                if (!state.spouse.isNullOrEmpty()) {
                    // Draw line from sui to spouse
                    SuiToSpouseLine(state)
                    SpouseRow(state)
                    SpouseToChildrenLine(state)
                    ChildrenRow(state)
                }
            }
        }
    }
}

@Composable
fun ParentRow(state: DetailState) {
    val father = state.father
    val mother = state.mother
    Row(
        modifier = Modifier
            .width(300.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        RelationModuleBox(
            modifier = Modifier
                .width(80.dp)
                .wrapContentHeight(),
            text = "父 \n ${father?.firstName ?: "未知"}"
        )
        RelationModuleBox(
            modifier = Modifier
                .width(80.dp)
                .wrapContentHeight(),
            text = "母 \n ${mother?.firstName ?: "未知"}"
        )
    }
}

@Composable
fun ParentToSuiLine() {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(50.dp)
    ) {
        val start = ((300f-160f)/3f + 40f)/300f
        DrawLine(startX = start, startY = 0f, endX = start, endY = 0.5f) // 父亲框底部到水平线
        DrawLine(startX = 1-start, startY = 0f, endX = 1-start, endY = 0.5f) // 母亲框底部到水平线
        DrawLine(startX = start, startY = 0.5f, endX = 1-start, endY = 0.5f) // 水平线
        DrawLine(startX = 0.5f, startY = 0.5f, endX = 0.5f, endY = 1f) // 水平线到Sui框顶部
    }
}

@Composable
fun SuiRow(state: DetailState) {
    val sui = state.selected!!
    Box(
        modifier = Modifier
            .width(80.dp)
            .wrapContentHeight()
    ) {
        RelationModuleBox(
            modifier = Modifier
                .width(80.dp)
                .wrapContentHeight(),
            text = sui.firstName
        )
    }
}

@Composable
fun SuiToSpouseLine(state: DetailState) {
    val spouse = state.spouse!!
    if (spouse.size == 1){
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(25.dp)
        ) {
            DrawLine(startX = 0.5f, startY = 0f, endX = 0.5f, endY = 1f)
        }
    }else{
        val width = (150f * spouse.size)
        val gap = (width-80f*spouse.size)/(spouse.size+1)
        val start = (gap + 40f)/width
        Box(
            modifier = Modifier
                .width(width.dp)
                .height(50.dp)
        ) {
            for (i in spouse.indices){
                DrawLine(startX = start+i*(gap+80f)/width, startY = 0.5f, endX = start+i*(gap+80f)/width, endY = 1f)
            }
            DrawLine(startX = start, startY = 0.5f, endX = 1-start, endY = 0.5f)
            DrawLine(startX = 0.5f, startY = 0f, endX = 0.5f, endY = 0.5f)
        }
    }

}

@Composable
fun SpouseRow(state: DetailState) {
    val spouse = state.spouse!!
    val width = (150 * spouse.size).dp

    Row(
        modifier = Modifier
            .width(width)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        spouse.forEach { sui: Sui ->
            RelationModuleBox(
                modifier = Modifier
                    .width(80.dp)
                    .wrapContentHeight(),
                text = "配偶 \n ${sui.firstName}"
            )
        }
    }
}

@Composable
fun SpouseToChildrenLine(state: DetailState) {
    val spouse = state.spouse!!
    if (spouse.size == 1){
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(25.dp)
        ) {
            DrawLine(startX = 0.5f, startY = 0f, endX = 0.5f, endY = 1f)
        }
    }else{
        val width = (150f * spouse.size)
        Box(
            modifier = Modifier
                .width(width.dp)
                .height(25.dp)
        ) {
            val gap = (width-80f*spouse.size)/(spouse.size+1)
            val start = (gap + 40f)/width
            for (i in spouse.indices){
                if(state.children?.any { sui: Sui -> sui.idMother== spouse[i].id } == true){
                    DrawLine(startX = start+i*(gap+80f)/width, startY = 0f, endX = start+i*(gap+80f)/width, endY = 1f)
                }
            }
        }
    }

}

@Composable
fun ChildrenRow(state: DetailState) {
    val spouse = state.spouse!!
    if (spouse.size == 1) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .width(150f.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            state.children?.forEach { child: Sui ->
                RelationModuleBox(
                    modifier = Modifier
                        .width(80.dp)
                        .wrapContentHeight(),
                    text = " ${child.firstName}"
                )
            }
        }
    } else {
        val width = (150 * spouse.size).dp
        Row(
            Modifier.width(width),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            spouse.forEach { sui: Sui ->
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .width(80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val childrenList = mutableListOf<Sui>()
                    state.children?.forEach { child ->
                        if (child.idMother == sui.id) {
                            childrenList.add(child)
                        }
                    }

                    childrenList.forEach { child: Sui ->
                        RelationModuleBox(
                            modifier = Modifier
                                .width(80.dp)
                                .wrapContentHeight(),
                            text = child.firstName
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RelationModuleBox(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier = modifier
            .border(4.dp, Color.Black)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun DrawLine(startX: Float, startY: Float, endX: Float, endY: Float) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawLine(
            color = Color.Black,
            start = Offset(startX * size.width, startY * size.height),
            end = Offset(endX * size.width, endY * size.height),
            strokeWidth = 8f
        )
    }
}

