package com.example.flaggametest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun MyApp() {
    val viewModel: MyViewModel = remember { MyViewModel() }
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Your UI components here
            Text("Screen Width: $screenWidthDp")
            Text("Screen Height: $screenHeightDp")
            MyList(viewModel.items)
        }
    }
}

@Composable
fun MyList(items: List<String>) {
    LazyColumn {
        items(items) { item ->
            Text(text = item)
        }
    }
}

class MyViewModel {
    var items by mutableStateOf(List(100) { "Item $it" })
}

@Composable
fun MyScreen() {
    val viewModel: MyViewModel = remember { MyViewModel() }
    val context = LocalContext.current
    val screenWidth = with(LocalDensity.current) { context.resources.configuration.screenWidthDp.dp }
    val screenHeight = with(LocalDensity.current) { context.resources.configuration.screenHeightDp.dp }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "Screen Width: $screenWidth")
            Text(text = "Screen Height: $screenHeight")
            MyList(items = viewModel.items)
        }
    }
}





