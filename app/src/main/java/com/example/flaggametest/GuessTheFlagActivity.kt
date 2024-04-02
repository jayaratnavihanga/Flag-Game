package com.example.flaggametest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flaggametest.ui.theme.FlagGameTestTheme

class GuessTheFlagActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlagGameTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessTheFlag()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessTheFlag() {
    val context = LocalContext.current
    val countries = remember { loadCountriesFromAssets(context) }
    val currentCountry by remember { mutableStateOf(countries.random()) }
    val countryToGuess = currentCountry.countryName
    val wrongCountry1 by remember { mutableStateOf(countries.random()) }
    val wrongCountry2 by remember { mutableStateOf(countries.random()) }
    var message by remember { mutableStateOf("") }
    var flagGuessed by remember { mutableStateOf(false) }
    val countryList = mutableListOf(currentCountry, wrongCountry1, wrongCountry2)
    countryList.shuffle()
    val x = remember { countryList[0] }
    val y = remember { countryList[1] }
    val z = remember { countryList[2] }

    Log.d("Country name", currentCountry.toString())

    if (!flagGuessed) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Guess The Flag")
                }
            )
            Text(text = countryToGuess)

            Image(
                painter = painterResource(id = getDrawableResourceId(x.countryCode.lowercase())),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .padding(10.dp)
                    .border(color = Color.Black, width = 5.dp)
                    .clickable {
                        message = if (x.countryName == countryToGuess) {
                            "CORRECT!"
                        } else {
                            "WRONG!"
                        }
                    },
            )



            Image(
                painter = painterResource(id = getDrawableResourceId(y.countryCode.lowercase())),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .padding(10.dp)
                    .border(color = Color.Black, width = 5.dp)
                    .clickable {
                        message = if (y.countryName == countryToGuess) {
                            "CORRECT!"
                        } else {
                            "WRONG!"
                        }
                    },

            )

            Image(
                painter = painterResource(id = getDrawableResourceId(z.countryCode.lowercase())),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .padding(10.dp)
                    .border(color = Color.Black, width = 5.dp)
                    .clickable {
                        message = if (z.countryName == countryToGuess) {
                            "CORRECT!"
                        } else {
                            "WRONG!"
                        }
                    },
            )

            Text(
                text = message,
                color = if (message == "CORRECT!") Color.Green else Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = {
                    flagGuessed = true
                }, modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(20.dp)
                    .width(100.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color.Black)


            ) {
                Text(text = "Next")

            }
        }
    } else {
        GuessTheFlag()
    }

}

