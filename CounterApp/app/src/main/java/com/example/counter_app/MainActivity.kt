package com.example.counter_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.counter_app.ui.theme.CounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterApp()
                }
            }
        }
    }
}

@Composable
fun CounterApp() {
    var count by rememberSaveable() { mutableStateOf(0) }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF2196F3),
            Color(0xFF1565C0)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "DH Counter App",
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF42A5F5),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Box(
            modifier = Modifier
                .size(240.dp, 260.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(gradientBrush),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$count",
                fontSize = 80.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFFEB3B)
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Buttons row
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Minus Button
            Button(
                onClick = {
                    if (count > 0) count--
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF505050)
                ),
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "−",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // Plus Button
            Button(
                onClick = { count++ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF42A5F5)
                ),
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "+",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (count == 0) "Negative numbers not allowed, please!" else "",
            color = Color(0xFFE53935),
            fontSize = 14.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CounterAppTheme {
        CounterApp()
    }
}