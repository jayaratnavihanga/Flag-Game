package com.example.flaggametest


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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


}


@Composable
fun GuessTheCountry() {
    val context = LocalContext.current
    val countries = remember { loadCountriesFromAssets(context) }
    var currentCountry by remember { mutableStateOf(countries.random()) }
    val countryNameList = countries.map { it.countryName }
    var selectedCountry by remember { mutableStateOf<String?>(null) }
    var submitted by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize(),
        Arrangement.Top,
        Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = getDrawableResourceId(currentCountry.countryCode.lowercase())),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 10.dp)
                .border(BorderStroke(5.dp, Color.Black))
        )

        LazyColumn(

            modifier = Modifier.weight(1f)
                .height(300.dp)
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

        SubmitNextButton(
            onClickSubmit = {
                submitted = true
            },
            onNextClicked = {
                currentCountry = countries.random()
                selectedCountry = null
                submitted = false
            }
        )

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

@Composable
fun SubmitNextButton(
    onClickSubmit: () -> Unit,
    onNextClicked: () -> Unit
) {
    val isSubmitted = remember { mutableStateOf(false) }

    val buttonText = if (isSubmitted.value) "Next" else "Submit"

    Row {
        Button(
            onClick = {
                if (isSubmitted.value) {
                    onNextClicked()
                } else {
                    onClickSubmit()
                }
                isSubmitted.value = !isSubmitted.value
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = buttonText)
        }
    }
}