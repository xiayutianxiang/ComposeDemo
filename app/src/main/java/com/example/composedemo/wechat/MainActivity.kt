package com.example.composedemo.wechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.R
import com.example.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme() {
                WeBottomBar(2)
            }
        }
    }
}

@Composable
private fun WeBottomBar(selected: Int) {
    Row {
        TabItem(
            if (selected == 0) R.drawable.head else R.drawable.ic_launcher_background, "聊天",
            Modifier.weight(1f)
        )
        TabItem(
            if (selected == 1) R.drawable.head else R.drawable.ic_launcher_background,
            "通讯录",
            Modifier.weight(1f)
        )
        TabItem(
            if (selected == 2) R.drawable.head else R.drawable.ic_launcher_background,
            "发现",
            Modifier.weight(1f)
        )
        TabItem(
            if (selected == 3) R.drawable.head else R.drawable.ic_launcher_background,
            "我的",
            Modifier.weight(1f)
        )
    }
}

@Composable
private fun TabItem(@DrawableRes iconId: Int, title: String, modifier: Modifier = Modifier) {
    //只给纵向加边距
    Column(
        modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
           painter =  painterResource(id = iconId), title,
            Modifier.size(30.dp)
        )

        //字体大小
        Text(text = title, fontSize = 13.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeBottomBarPreview() {
    WeBottomBar(2)
}

@Preview(showBackground = true)
@Composable
fun TabItemPreView() {
    TabItem(iconId = R.drawable.head, title = "聊天")
}