package com.example.flaggametest

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flaggametest.ui.theme.FlagGameTestTheme
import java.time.format.TextStyle

class GuessHintsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlagGameTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    GuessHints()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessHints() {
    val context = LocalContext.current
    val countries = remember { loadCountriesFromAssets(context) }
    val currentCountry by remember { mutableStateOf(countries.random()) }
    val countryToGuess = currentCountry.countryName
    var guessedWord by remember { mutableStateOf("-".repeat(countryToGuess.length)) }
    var remainingAttempts by remember { mutableIntStateOf(3) }
    var userInput by remember { mutableStateOf(TextFieldValue()) }
    var isNext by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }
    var gameWon by remember { mutableStateOf(false) }
    var displayWord by remember { mutableStateOf("") }
    Log.d("Country name", currentCountry.toString())

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
                Text("Guess Hints")
            }
        )

        if (!gameWon) {
            Image(
                painter = painterResource(id = getDrawableResourceId(currentCountry.countryCode.lowercase())),
                contentDescription = null,
                modifier = Modifier
                    .padding(50.dp)
                    .border(color = Color.Black, width = 5.dp),
            )

            displayWord = if (remainingAttempts != 0) {
                guessedWord
            } else {""}
            Text(
                text = displayWord,
                style = MaterialTheme.typography.headlineLarge
            )
            Row {
                BasicTextField(
                    value = userInput,
                    onValueChange = {
                        userInput = it
                    },
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(fontSize = 28.sp),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(50.dp)
                        .height(50.dp)
                        .border(2.dp, Color.Black)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))
            if (guessedWord != countryToGuess) {
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
                                    isCorrect = true
                                }
                            } else {
                                remainingAttempts--
                                if (remainingAttempts == 0) {
                                    guessedWord = countryToGuess
                                }
                            }
                        }
                        userInput = TextFieldValue()
                    },
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    modifier = Modifier.align(Alignment.CenterHorizontally),

                    ) {
                    Text(text = "Submit")
                }
            }

            if (remainingAttempts == 0) {
                Row {

                    Text(
                        text = "WRONG!    ",
                        color = Color.Red,
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Text(
                        text = countryToGuess,
                        color = Color.Blue,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                isNext = true
            }

            if (isCorrect) {
                Text(
                    text = "CORRECT!",
                    color = Color.Green,
                    style = MaterialTheme.typography.headlineLarge
                )
                isNext = true
            }

            if (isNext) {
                Button(
                    onClick = {
                        gameWon = true
                    },
                    colors = ButtonDefaults.buttonColors(Color.Black), // Set background color to black
                ) {
                    Text(text = "Next")
                }
            }
        } else {
            GuessHints()
        }
    }
}
