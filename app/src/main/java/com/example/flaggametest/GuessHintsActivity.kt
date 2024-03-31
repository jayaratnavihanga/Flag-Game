package com.example.flaggametest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.flaggametest.ui.theme.FlagGameTestTheme


class GuessHintsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlagGameTestTheme {
                GuessHints()
            }
        }
    }
}

@Composable
fun GuessHints() {
    val context = LocalContext.current
    val countries = remember { loadCountriesFromAssets(context) }

    var remainingAttempts by remember { mutableIntStateOf(3) }
    var isNext by remember { mutableStateOf(false) }
    val gameWon by remember { mutableStateOf(false) }
    var currentCountryIndex by remember { mutableIntStateOf(0) }

    val currentCountry = remember { countries[currentCountryIndex] }
    val countryToGuess = remember { currentCountry.countryName }

    var guessedWord by remember { mutableStateOf("-".repeat(countryToGuess.length)) }
    var userInput by remember { mutableStateOf(TextFieldValue()) }

    if (!gameWon) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = getDrawableResourceId(currentCountry.countryCode.lowercase())),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text(text = countryToGuess)

            Text(
                text = guessedWord,
                style = MaterialTheme.typography.bodyMedium
            )

            BasicTextField(
                value = userInput,
                onValueChange = {
                    userInput = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            if (guessedWord != countryToGuess && remainingAttempts > 0) {
                Button(
                    onClick = {
                        if (userInput.text.isNotEmpty()) {
                            if (countryToGuess.contains(userInput.text, ignoreCase = true)) {
                                guessedWord = guessedWord.mapIndexed { index, char ->
                                    if (countryToGuess[index].equals(
                                            userInput.text[0],
                                            ignoreCase = true
                                        )
                                    ) {
                                        countryToGuess[index]
                                    } else {
                                        char
                                    }
                                }.joinToString("")
                                if (guessedWord.equals(countryToGuess, ignoreCase = true)) {
                                    isNext = true
                                }
                            } else {
                                remainingAttempts--
                            }
                        }
                        userInput = TextFieldValue()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Submit")
                }
            }

            if (remainingAttempts == 0) {
                Text(
                    text = "Wrong! The country is $countryToGuess",
                    style = MaterialTheme.typography.bodyMedium
                )
                isNext = true
            }

            if (isNext) {
                Text(
                    text = "Correct!",
                    style = MaterialTheme.typography.bodyMedium
                )
                Button(
                    onClick = {
                        currentCountryIndex = (currentCountryIndex + 1) % countries.size
                        remainingAttempts = 3
                        guessedWord = "-".repeat(countryToGuess.length)
                        isNext = false
                    }, modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}

