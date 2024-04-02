package com.example.flaggametest


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flaggametest.ui.theme.FlagGameTestTheme
import kotlin.random.Random
import kotlin.random.nextInt

class GuessTheCountryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlagGameTestTheme {
                // A surface container using the 'background' color from the theme
                GuessTheCountry()
            }
        }
    }


    @Composable
    fun GuessTheCountry() {
        val context = LocalContext.current
        val activity = this
        val countries = remember { loadCountriesFromAssets(context) }
        var currentCountryIndex by rememberSaveable { mutableIntStateOf(-1) }
        var selectedCountry by rememberSaveable { mutableStateOf<String?>(null) }
        var submitted by rememberSaveable { mutableStateOf(false) }
        var buttonCount by rememberSaveable { mutableIntStateOf(0) }
        var buttonText by rememberSaveable { mutableStateOf("Submit") }
        val countryNameList = countries.map { it.countryName }
        if (currentCountryIndex == -1) {
            currentCountryIndex = Random.nextInt(0..255)
        }
        val currentCountry = countries[currentCountryIndex]
        Log.d("Country name", currentCountry.toString())




        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = getDrawableResourceId(currentCountry.countryCode.lowercase())),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillWidth
            )

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(countryNameList) { countryName ->
                    val isSelected = countryName == selectedCountry
                    Text(
                        text = countryName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                selectedCountry = countryName
                            },
                        color = if (isSelected) Color.Blue else Color.Black
                    )
                }
            }

            Button(onClick = {
                if (buttonCount == 0) {
                    buttonText = "Next"
                    buttonCount++
                    submitted = true

                } else {
                    activity.finish()
                    activity.startActivity(activity.intent)

                }
            }) {
                Text(text = buttonText)
            }

            if (submitted) {
                val isCorrect = selectedCountry == currentCountry.countryName
                val message = if (isCorrect) "Correct!" else "Incorrect!"
                val color = if (isCorrect) Color.Green else Color.Red

                Row {
                    Text(
                        text = message,
                        color = color,
                        modifier = Modifier.padding(16.dp)
                    )
                    if (!isCorrect) {
                        Text(
                            text = currentCountry.countryName,
                            color = Color.Blue,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                }
            }
        }

    }

}
