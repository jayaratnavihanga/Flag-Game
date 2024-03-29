package com.example.flaggametest



import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp



/*
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



    if (!flagGuessed){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = countryToGuess)

        Image(
            painter = painterResource(id = getDrawableResourceId(x.countryCode.lowercase())),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable {
                    message = if (x.countryName == countryToGuess) {
                        "CORRECT!"
                    } else {
                        "WRONG!"
                    }
                }
        )

        Image(
            painter = painterResource(id = getDrawableResourceId(y.countryCode.lowercase())),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable {
                    message = if (y.countryName == countryToGuess) {
                        "CORRECT!"
                    } else {
                        "WRONG!"
                    }
                }

        )

        Image(
            painter = painterResource(id = getDrawableResourceId(z.countryCode.lowercase())),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable {
                    message = if (z.countryName == countryToGuess) {
                        "CORRECT!"
                    } else {
                        "WRONG!"
                    }
                }
        )

        Text(
            text = message,
            color = if (message == "CORRECT!") androidx.compose.ui.graphics.Color.Green else androidx.compose.ui.graphics.Color.Red
            , modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = {
                flagGuessed = true
            }, modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Next")

        }
    }
}else{
    GuessTheFlag()
}

}

*/
