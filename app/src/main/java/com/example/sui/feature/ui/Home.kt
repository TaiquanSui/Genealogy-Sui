package com.example.sui.feature.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.sui.R
import com.example.sui.feature.ui.Screen

@Composable
fun Home(navController: NavController){
    ConstraintLayout {
        val (title, filter, text) = createRefs()

        Row (
            modifier = Modifier.constrainAs(title){
                top.linkTo(parent.top)
                bottom.linkTo(parent.top)
                verticalChainWeight = 0.3f
            }
        ){
            Image(painter = painterResource(id = R.drawable.sui), contentDescription = "sui")
            Image(painter = painterResource(id = R.drawable.shi), contentDescription = "shi")
            Image(painter = painterResource(id = R.drawable.zu), contentDescription = "zu")
            Image(painter = painterResource(id = R.drawable.pu), contentDescription = "pu")
        }

        Column (
            modifier = Modifier
                .constrainAs(filter) {
                    top.linkTo(title.bottom)
                    bottom.linkTo(parent.bottom)
                }
                .height(Dp(300f))
                .width(Dp(200f))
        ){
            Row {
                Text(text = "按名字：")
                TextField(value = "", onValueChange = {})
            }
            Row {
                Text(text = "按世代：")
                TextField(
                    value = "",
                    onValueChange = {} ,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "查询")
            }
        }

        ClickableText(text = AnnotatedString("查看全谱→"),
            modifier = Modifier.constrainAs(filter) {
                bottom.linkTo(parent.bottom)
                absoluteRight.linkTo(parent.absoluteRight)
            }
                .height(Dp(80f))
                .width(Dp(40f)),
            onClick ={
                navController.navigate(Screen.FullGenealogyScreen.route);
            }
        )

    }

}

