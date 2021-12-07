package com.jiliapp.platform.compose

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jiliapp.platform.compose.ui.theme.PlatformComposeTheme
// Compose   LiveData   Flow   RxJava2 sharedp
//Compose数据源
//实现无状态的一种简单方法是使用状态提升。

class HelloActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlatformComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HelloContent()
                }
            }
        }
    }
}


/*
@Parcelize
data class City(val name: String, val country: String) : Parcelable

@Composable
fun CityScreen() {
    var selectedCity = rememberSaveable {
        mutableStateOf(City("Madrid", "Spain"))
    }
}
*/



@Composable
@Preview
fun PreviewHelloContent() {
    HelloScreen()
}

/**
 * 单一可信来源：我们会通过移动状态而不是复制状态，来确保只有一个可信来源。这有助于避免 bug。
         封装：只有有状态可组合项能够修改其状态。这完全是内部的。
       可共享：可与多个可组合项共享提升的状态。如果想在另一个可组合项中执行 name 操作，可以通过变量提升来做到这一点。
      可拦截：无状态可组合项的调用方可以在更改状态之前决定忽略或修改事件。
       解耦：无状态 ExpandingCard 的状态可以存储在任何位置。例如，现在可以将 name 移入 ViewModel。
 */

//变更为有状态
@Composable
fun HelloScreen() {
    var name by rememberSaveable { mutableStateOf("") }
    HelloContent1(name = name, onNameChange = { name = it })
}

//无状态
@Composable
fun HelloContent1(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello, $name",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") }
        )
    }
}



//有状态
@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {

        var name by remember { mutableStateOf("") }
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )

//
//        Text(
//            text = "Hello! kitty",
//            modifier = Modifier.padding(bottom = 8.dp),
//            style = MaterialTheme.typography.h5
//        )
        OutlinedTextField(
            value = "",
            onValueChange = { name = it },
            label = { Text("Name") }
        )
    }
}

