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


open class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


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
                        val navigate =
                            Intent(this@MainActivity, GuessTheCountryActivity::class.java)
                        startActivity(navigate)


                    }
                ) {
                    Text(text = "Guess the Country")
                }

                Spacer(modifier = Modifier.height(16.dp)) // Add spacing between buttons

                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp),
                    onClick = {
                        val navigate = Intent(this@MainActivity, GuessHintsActivity::class.java)
                        startActivity(navigate)
                    }
                ) {
                    Text(text = "Guess-Hints")
                }

                Spacer(modifier = Modifier.height(16.dp)) // Add spacing between buttons

                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp),
                    onClick = {
                        val navigate = Intent(this@MainActivity, GuessTheFlagActivity::class.java)
                        startActivity(navigate)

                    }
                ) {
                    Text(text = "Guess the Flag")
                }

                Spacer(modifier = Modifier.height(16.dp)) // Add spacing between buttons

                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp),
                    onClick = {
                        val navigate = Intent(this@MainActivity, AdvancedLevelActivity::class.java)
                        startActivity(navigate)
                    }
                ) {
                    Text(text = "Advanced Level")
                }
            }
        }
    }
}













