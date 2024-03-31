package com.example.flaggametest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flaggametest.ui.theme.FlagGameTestTheme

class GuessTheFlagActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlagGameTestTheme {
                GuessTheFlag()
            }
        }
    }
}

@Composable
fun GuessTheFlag() {
    val context = LocalContext.current
    val countries = remember { loadCountriesFromAssets(context) }

    var message by rememberSaveable { mutableStateOf("") }
    var flagGuessed by rememberSaveable { mutableStateOf(false) }
    var currentCountryIndex by rememberSaveable { mutableIntStateOf(0) }

    val currentCountry = countries[currentCountryIndex]
    val countryToGuess = currentCountry.countryName

    val countryList = mutableListOf(currentCountry).apply {
        addAll(countries.shuffled().take(2))
    }.shuffled()

    if (!flagGuessed) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = countryToGuess)

            countryList.forEach { country ->
                Image(
                    painter = painterResource(id = getDrawableResourceId(country.countryCode.lowercase())),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickable {
                            message = if (country.countryName == countryToGuess) {
                                "CORRECT!"
                            } else {
                                "WRONG!"
                            }
                        }
                )
            }

            Text(
                text = message,
                color = if (message == "CORRECT!") Color.Green else Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = {
                    currentCountryIndex = (currentCountryIndex + 1) % countries.size
                    message = ""
                    flagGuessed = false
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Next")
            }
        }
    }
}
