package com.example.tip_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tip_calculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TipCalculator(name: String, modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }
    var amount = amountInput.toDoubleOrNull()?: 0.0

    var tipInput by remember { mutableStateOf("") }
    var tipPercent = tipInput.toDoubleOrNull()?:0.0

    var roundUp by remember { mutableStateOf(false) }

    var tip = CalculateTip(amount, tipPercent, roundUp)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column(){
            Text(
                text = "Calculator Tip",
                //modifier = modifier
            )
            TextField(
                value = amountInput,
                label = { Text("Bill Amount") },
                onValueChange = { amountInput = it },
                //singleLine = true,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            TextField(
                value = tipInput,
                label = { Text("Tip Percentage") },
                onValueChange = { tipInput = it },
                //singleLine = true,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Round up tip?",
                    fontSize = 20.sp
                )
                Switch(
                    checked = true,
                    onCheckedChange = { },
                    modifier = Modifier.padding(start = 95.dp)
                )
            }
            Text(
                text = "Tip Amount: $tip",
                modifier = Modifier.align(alignment = Alignment.End),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

internal fun CalculateTip(amount:Double, tipPercent:Double = 15.0, roundUp: Boolean): String {
    var tip = tipPercent / 100 * amount
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip);
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalculatorTheme {
        TipCalculator("Android")
    }
}