package com.example.flaggametest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flaggametest.ui.theme.FlagGameTestTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlagGameTestTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White,
        ),
        title = {
            Text("  Flag Game")
        }
    )


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = {
                val navigate = Intent(context, GuessTheCountryActivity::class.java)
                context.startActivity(navigate)
            },
            modifier = Modifier
                .height(50.dp)
                .width(200.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Guess the Country", color = Color.Black)
        }


        OutlinedButton(
            onClick = {
                val navigate = Intent(context, GuessHintsActivity::class.java)
                context.startActivity(navigate)
            },
            modifier = Modifier
                .height(50.dp)
                .width(200.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Guess-Hints", color = Color.Black)
        }

        OutlinedButton(
            onClick = {
                val navigate = Intent(context, GuessTheFlagActivity::class.java)
                context.startActivity(navigate)
            },
            modifier = Modifier
                .height(50.dp)
                .width(200.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Guess the Flag", color = Color.Black)
        }

        OutlinedButton(
            onClick = {
                val navigate = Intent(context, AdvancedLevelActivity::class.java)
                context.startActivity(navigate)
            },
            modifier = Modifier
                .height(50.dp)
                .width(200.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Advanced Level", color = Color.Black)
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    FlagGameTestTheme {
        MainScreen()
    }
}



