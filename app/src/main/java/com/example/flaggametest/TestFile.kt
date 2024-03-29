package com.example.flaggametest

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Test() {

    Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Button(
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            onClick = {


            }
        ) {
            Text(text = "Guess the Country")
        }

        Spacer(modifier = Modifier.height(16.dp)) // Add spacing between buttons

        Button(
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Guess-Hints")
        }

        Spacer(modifier = Modifier.height(16.dp)) // Add spacing between buttons

        Button(
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Guess the Flag")
        }

        Spacer(modifier = Modifier.height(16.dp)) // Add spacing between buttons

        Button(
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Advanced Level")
        }
    }
}


