package com.jiliapp.platform.compose

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jiliapp.platform.compose.ui.theme.PlatformComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlatformComposeTheme() {
                // A surface container using the 'background' color from the theme
                Surface() {
                    MessageCard(Message("zhangsan","go go go "))
                }
            }

        }
    }
}


data class Message(val author: String, val body: String)

// Row  Box
@Composable
fun MessageCard(message: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(painter = painterResource(id = R.mipmap.profile_picture)
            , contentDescription = "Contact profile picture"
        ,modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column() {
            Text(text = message.author,color = MaterialTheme.colors.secondaryVariant,style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = Modifier.width(4.dp))
           androidx.compose.material.Surface(shape = MaterialTheme.shapes.medium,elevation =1.0.dp) {
               Text(text = message.body,modifier = Modifier.padding(all = 4.0.dp),style = MaterialTheme.typography.body2)
           }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    PlatformComposeTheme(){
        MessageCard(message = Message("zhangsan","hello,word!"))
    }
}

@Composable
fun Conversation(list: List<Message>) {
    LazyColumn {
        items(list) { message ->
            MessageCard(message)
        }
    }
}


@Preview
@Composable
fun PreviewConversation() {
    PlatformComposeTheme() {
        Conversation(SampleData.conversationSample)
    }
}
