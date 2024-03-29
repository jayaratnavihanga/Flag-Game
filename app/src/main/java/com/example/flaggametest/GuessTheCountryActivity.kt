package com.example.flaggametest


import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flaggametest.ui.theme.FlagGameTestTheme

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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}

@Composable
fun GuessTheCountry() {
    val context = LocalContext.current
    val countries = remember { loadCountriesFromAssets(context) }
    var currentCountry by remember { mutableStateOf(countries.random()) }
    val countryNameList = countries.map { it.countryName }
    var selectedCountry by remember { mutableStateOf<String?>(null) }
    var submitted by remember { mutableStateOf(false) }

    Column {
        Image(
            painter = painterResource(id = getDrawableResourceId(currentCountry.countryCode.lowercase())),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { submitted = true },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Submit")
            }

            Button(
                onClick = {
                    currentCountry = countries.random()
                    selectedCountry = null
                    submitted = false
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Next")
            }
        }

        // Display a message when the submit button is clicked
        if (submitted) {
            val isCorrect = selectedCountry == currentCountry.countryName
            val message = if (isCorrect) "Correct!" else "Incorrect!"
            val color = if (isCorrect) Color.Green else Color.Red

            Text(
                text = message,
                color = color,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
