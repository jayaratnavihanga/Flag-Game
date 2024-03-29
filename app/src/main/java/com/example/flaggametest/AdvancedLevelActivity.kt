package com.example.flaggametest

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flaggametest.ui.theme.FlagGameTestTheme

class AdvancedLevelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlagGameTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AdvancedLevel()                }
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
fun AdvancedLevel() {
    val context = LocalContext.current
    val countries = remember { loadCountriesFromAssets(context) }
    var incorrectAttempts by remember { mutableStateOf(0) }
    var message by remember { mutableStateOf("") }
    var flagGuessed by remember { mutableStateOf(false) }
    var userInput1 by remember { mutableStateOf("") }
    var userInput2 by remember { mutableStateOf("") }
    var userInput3 by remember { mutableStateOf("") }
    var input1Correct by remember { mutableStateOf(false) }
    var input2Correct by remember { mutableStateOf(false) }
    var input3Correct by remember { mutableStateOf(false) }
    var correctCountries = remember { mutableStateOf<List<String>>(emptyList()) }
    var flag1 by remember { mutableStateOf(countries.random()) }
    var flag2 by remember { mutableStateOf(countries.random()) }
    var flag3 by remember { mutableStateOf(countries.random()) }
    var score by remember { mutableStateOf(0) }


    fun checkAnswers() {
        input1Correct = userInput1.equals(flag1.countryName, ignoreCase = true)
        input2Correct = userInput2.equals(flag2.countryName, ignoreCase = true)
        input3Correct = userInput3.equals(flag3.countryName, ignoreCase = true)

        if (!input1Correct) correctCountries.value = correctCountries.value + flag1.countryName
        if (!input2Correct) correctCountries.value = correctCountries.value + flag2.countryName
        if (!input3Correct) correctCountries.value = correctCountries.value + flag3.countryName

        if (input1Correct && input2Correct && input3Correct) {
            message = "CORRECT!"
        } else {
            message = "Incorrect! Try again."
            incorrectAttempts++
            if (incorrectAttempts == 3) {
                message = "WRONG!"
            }
        }
    }

    fun resetGame() {
        incorrectAttempts = 0
        message = ""
        flagGuessed = false
        userInput1 = ""
        userInput2 = ""
        userInput3 = ""
        input1Correct = false
        input2Correct = false
        input3Correct = false
        correctCountries.value = emptyList()
        flag1 = countries.random()
        flag2 = countries.random()
        flag3 = countries.random()
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Countries: ${flag1.countryName}, ${flag2.countryName}, ${flag3.countryName}",
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Score: $score",
            modifier = Modifier
                .padding(top = 8.dp, end = 16.dp)
                .align(Alignment.End)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = getDrawableResourceId(flag1.countryCode.lowercase())),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .padding(4.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            BasicTextField(
                value = userInput1,
                onValueChange = { userInput1 = it },
                singleLine = true,
                readOnly = input1Correct,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (input1Correct) Color.Gray else Color.Red,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(if (input1Correct) Color.LightGray else Color.White)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        if (message == "WRONG!" && !input1Correct) {
            Text(
                text = flag1.countryName,
                color = Color.Blue,
                modifier = Modifier.padding(top = 8.dp, start = 8.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = getDrawableResourceId(flag2.countryCode.lowercase())),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .padding(4.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            BasicTextField(
                value = userInput2,
                onValueChange = { userInput2 = it },
                singleLine = true,
                readOnly = input2Correct,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (input2Correct) Color.Gray else Color.Red,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(if (input2Correct) Color.LightGray else Color.White)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        if (message == "WRONG!" && !input2Correct) {
            Text(
                text = flag2.countryName,
                color = Color.Blue,
                modifier = Modifier.padding(top = 8.dp, start = 8.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = getDrawableResourceId(flag3.countryCode.lowercase())),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .padding(4.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            BasicTextField(
                value = userInput3,
                onValueChange = { userInput3 = it },
                singleLine = true,
                readOnly = input3Correct,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (input3Correct) Color.Gray else Color.Red,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(if (input3Correct) Color.LightGray else Color.White)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        if (message == "WRONG!" && !input3Correct) {
            Text(
                text = flag3.countryName,
                color = Color.Blue,
                modifier = Modifier.padding(top = 8.dp, start = 8.dp)
            )
            flagGuessed=true
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (!flagGuessed) {
                    checkAnswers()
                    flagGuessed = message == "CORRECT!"

                } else {
                    resetGame()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (flagGuessed) "Next" else "Submit")
        }

        if (message == "WRONG!") {
            correctCountries.value.forEach { correctCountry ->
                Text(
                    text = correctCountry,
                    color = Color.Blue,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Text(
            text = message,
            color = if (message == "CORRECT!") Color.Green else Color.Red,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Countries: ${flag1.countryName}, ${flag2.countryName}, ${flag3.countryName}",
            modifier = Modifier.padding(top = 16.dp)
        )

        if (flagGuessed){
            if (input1Correct){
                score++

            }
            if (input2Correct){
                score++
            }
            if (input3Correct){
                score++
            }

        }



    }
}
