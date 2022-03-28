package com.example.composedemo

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.data.Message
import com.example.composedemo.data.SampleData
import com.example.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(Message("山杉", "Hello Compose"))
                }
            }
        }
    }
}

@Composable
fun Greeting(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.head),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.size(8.dp))

        var isExpanded by remember {
            mutableStateOf(false)
        }

        val surfaceColor: Color by animateColorAsState(if (isExpanded) MaterialTheme.colors.primary
                else MaterialTheme.colors.surface,
        )

        Column(verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.name,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            androidx.compose.material.Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.say,
                    style = MaterialTheme.typography.body2,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

//显示黑色主题下背景
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun DefaultDarkPreview() {
    ComposeDemoTheme {
        Surface() {
            Greeting(msg = Message("山杉", "Hello Compose!"))
        }
    }
}

//显示白色主题下背景
@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Composable
fun DefaultLightPreview() {
    ComposeDemoTheme {
        Surface() {
            Greeting(msg = Message("山杉", "Hello Compose!"))
        }
    }
}

@Composable
fun Conversation(messages: List<Message>){
    LazyColumn(){
        items(messages){
            message->
            Greeting(msg = message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConversation(){
    ComposeDemoTheme() {
        Conversation(SampleData.conversationSample)
    }
}
