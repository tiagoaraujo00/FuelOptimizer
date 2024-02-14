@file:OptIn(ExperimentalMaterial3Api::class)

package com.tiago.fueloptimizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tiago.fueloptimizer.ui.theme.FuelOptimizerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FuelOptimizerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    App()
                }
            }
        }
    }
}


@Composable
fun App() {

    var gasolineValue by remember {
        mutableStateOf("")
    }

    var alcoholValue by remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .background(color = Color(0xFF009688))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Álcool ou Gasolina",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 32.sp)
            )

            if (alcoholValue.isNotBlank() && gasolineValue.isNotBlank()) {
                val gasolineOrAlcohol = alcoholValue.toDouble() / gasolineValue.toDouble() > 0.7
                val fuelDefine = if (gasolineOrAlcohol) "Gasolina" else "Alcool"
                val colorDefine = if (gasolineOrAlcohol) Color.Red else Color.Blue
                Text(
                    text = fuelDefine, style = TextStyle(
                        color = colorDefine,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            TextField(
                value = gasolineValue,
                onValueChange = {
                    gasolineValue = it
                },
                label = {
                    Text(text = "Gasolina")
                },
            )
            TextField(
                value = alcoholValue,
                onValueChange = {
                    alcoholValue = it
                },
                label = {
                    Text(text = "Álcool")
                },
            )
        }
    }
}
