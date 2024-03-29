package com.example.flaggametest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


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
                    onClick = {    val navigate = Intent(this@MainActivity, AdvancedLevelActivity::class.java)
                        startActivity(navigate) }
                ) {
                    Text(text = "Advanced Level")
                }
            }
        }
    }
}













