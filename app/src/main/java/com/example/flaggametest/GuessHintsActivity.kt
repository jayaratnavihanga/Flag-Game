package com.example.flaggametest

import android.content.res.Configuration
import android.os.Bundle
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Handle configuration changes here
        // For example, you may want to re-layout your UI elements
    }


}

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



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        if (!gameWon) {
            Image(
                painter = painterResource(id = getDrawableResourceId(currentCountry.countryCode.lowercase())),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(BorderStroke(5.dp, Color.Black))

            )


            Text(
                text = guessedWord,
                style = MaterialTheme.typography.headlineLarge
            )

            BasicTextField(
                value = userInput,
                onValueChange = {
                    userInput = it
                },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(fontSize = 28.sp)

                ,
                        modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(1.dp, Color.Black)
            )
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
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Submit")
                }
            }

            if (remainingAttempts == 0) {
               Row {

                   Text(
                       text = "WRONG!    ",
                       color = Color.Red,
                       style = MaterialTheme.typography.bodyLarge
                   )


                   Text(
                       text = countryToGuess,
                       color = Color.Blue,
                       style = MaterialTheme.typography.bodyMedium
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
                isNext=true


            }
            if (isNext){
                Button(
                    onClick = {
                        gameWon = true
                    },
                ) {
                    Text(text = "Next")

                }
            }

            Text(text = countryToGuess)


        } else {
            GuessHints()
        }
    }
}
