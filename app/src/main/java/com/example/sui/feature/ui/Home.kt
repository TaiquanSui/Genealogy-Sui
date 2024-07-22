//package com.example.sui.feature.ui
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.text.ClickableText
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.SpanStyle
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.withStyle
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.constraintlayout.compose.ConstraintLayout
//import androidx.navigation.NavController
//import com.example.sui.R
//
//
//@Composable
//fun Home(navController: NavController){
//    ConstraintLayout (
//        modifier = Modifier.fillMaxSize()
//    ){
//        val (title, filter, text) = createRefs()
//
//        Row (
//            modifier = Modifier.constrainAs(title){
//                top.linkTo(parent.top, margin = 150.dp)
//                absoluteRight.linkTo(parent.absoluteRight)
//                absoluteLeft.linkTo(parent.absoluteLeft)
//
//            }
//        ){
//            Image(painter = painterResource(id = R.drawable.sui), contentDescription = "sui", Modifier.size(100.dp,100.dp))
//            Image(painter = painterResource(id = R.drawable.shi), contentDescription = "shi", Modifier.size(100.dp,100.dp))
//            Image(painter = painterResource(id = R.drawable.zu), contentDescription = "zu", Modifier.size(100.dp,100.dp))
//            Image(painter = painterResource(id = R.drawable.pu), contentDescription = "pu", Modifier.size(100.dp,100.dp))
//        }
//
//        Column (
//            modifier = Modifier
//                .constrainAs(filter) {
//                    top.linkTo(title.bottom)
//                    bottom.linkTo(parent.bottom)
//                    absoluteRight.linkTo(parent.absoluteRight)
//                    absoluteLeft.linkTo(parent.absoluteLeft)
//                }
//                .width(300.dp)
//                .height(200.dp)
//
//        ){
//            Row (
//                Modifier.align(Alignment.CenterHorizontally)
//            ){
//                Text(
//                    text = "按名字：",
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//
//                TextField(value = "", onValueChange = {})
//            }
//            Row (
//                Modifier.align(Alignment.CenterHorizontally)
//            ){
//                Text(
//                    text = "按世代：",
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//                TextField(
//                    value = "",
//                    onValueChange = {} ,
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                )
//            }
//            Button(
//                onClick = { /*TODO*/ },
//                Modifier.align(Alignment.CenterHorizontally)
//            ) {
//                Text(text = "查询")
//            }
//        }
//
//        val annotatedText = remember {
//            buildAnnotatedString {
//                withStyle(style = SpanStyle(color = Color.Blue, fontSize = 14.sp, fontWeight = FontWeight.Normal)) {
//                    append("查看全谱→")
//                }
//            }
//        }
//
//        ClickableText(text = annotatedText,
//            modifier = Modifier.constrainAs(text) {
//                bottom.linkTo(parent.bottom)
//                absoluteRight.linkTo(parent.absoluteRight)
//            }
//                .width(Dp(80f))
//                .height(Dp(30f)),
//
//
//            onClick ={
//                navController.navigate(Screen.DetailScreen.route);
//            }
//        )
//
//    }
//
//}
