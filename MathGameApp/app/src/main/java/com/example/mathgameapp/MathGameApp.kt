package com.example.mathgameapp

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathgameapp.ui.theme.*
import kotlin.random.Random

// Data Model

data class MathQuestion(val a: Int, val b: Int) {
    val answer: Int get() = a + b
}

data class GameState(
    val questions: List<MathQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val correctCount: Int = 0,
    val wrongCount: Int = 0,
    val userAnswers: List<Int?> = emptyList()
)

sealed class Screen {
    object Start : Screen()
    object Game  : Screen()
    object Result: Screen()
}

// Main Composable

@Composable
fun MathGameApp() {
    var screen    by remember { mutableStateOf<Screen>(Screen.Start) }
    var gameState by remember { mutableStateOf(GameState()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {
        AnimatedContent(
            targetState = screen,
            transitionSpec = {
                fadeIn(tween(400)) + slideInVertically(tween(400)) { it / 12 } togetherWith
                        fadeOut(tween(200))
            },
            label = "screen_transition"
        ) { currentScreen ->
            when (currentScreen) {
                Screen.Start -> StartScreen { totalQuestions ->
                    val questions = List(totalQuestions) {
                        MathQuestion(Random.nextInt(1, 50), Random.nextInt(1, 50))
                    }
                    gameState = GameState(
                        questions    = questions,
                        userAnswers  = List(totalQuestions) { null }
                    )
                    screen = Screen.Game
                }

                Screen.Game -> QuestionScreen(
                    gameState = gameState,
                    onAnswer  = { answer ->
                        val q          = gameState.questions[gameState.currentIndex]
                        val correct    = answer == q.answer
                        val newAnswers = gameState.userAnswers.toMutableList()
                        newAnswers[gameState.currentIndex] = answer
                        val nextIndex  = gameState.currentIndex + 1
                        val newState   = gameState.copy(
                            correctCount = gameState.correctCount + if (correct) 1 else 0,
                            wrongCount   = gameState.wrongCount   + if (correct) 0 else 1,
                            currentIndex = nextIndex,
                            userAnswers  = newAnswers
                        )
                        gameState = newState
                        if (nextIndex >= newState.questions.size) {
                            screen = Screen.Result
                        }
                    },
                    onCancel = {
                        screen    = Screen.Start
                        gameState = GameState()
                    }
                )

                Screen.Result -> ResultScreen(
                    gameState = gameState,
                    onPlayAgain = {
                        screen    = Screen.Start
                        gameState = GameState()
                    }
                )
            }
        }
    }
}

// Start Screen

@Composable
fun StartScreen(onStart: (Int) -> Unit) {
    var inputText by remember { mutableStateOf("") }
    val questionCount = inputText.toIntOrNull()?.coerceIn(1, 50)
    val isValid = questionCount != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Badge
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(InkBlack)
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text("MATH APP", fontSize = 11.sp, fontWeight = FontWeight.Bold,
                color = SoftGold, letterSpacing = 3.sp)
        }

        Spacer(Modifier.height(24.dp))

        // Title
        Text(
            text = "Matenik.",
            style = TextStyle(
                fontSize = 52.sp,
                fontWeight = FontWeight.Black,
                color = InkBlack,
                lineHeight = 56.sp
            )
        )

        Spacer(Modifier.height(8.dp))

        Text(
            "fast & curious.",
            fontSize = 15.sp,
            color = MidGray,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(48.dp))

        // Input card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .border(2.dp, if (isValid) AccentMint else LightGray, RoundedCornerShape(20.dp))
                .padding(24.dp)
        ) {
            Column {
                Text("HOW MANY QUESTIONS?", fontSize = 10.sp,
                    fontWeight = FontWeight.Bold, color = MidGray, letterSpacing = 2.sp)
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { if (it.length <= 2) inputText = it },
                    placeholder = { Text("e.g. 10", color = MidGray, fontSize = 32.sp, fontWeight = FontWeight.Bold) },
                    textStyle = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Black, color = InkBlack),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor   = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor          = AccentCoral
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                if (inputText.isNotEmpty() && !isValid) {
                    Text("Enter a number between 1 and 50", fontSize = 12.sp, color = AccentCoral)
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Start button
        Button(
            onClick = { questionCount?.let { onStart(it) } },
            enabled = isValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = InkBlack,
                disabledContainerColor = LightGray
            )
        ) {
            Text(
                "START →",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = if (isValid) SoftGold else MidGray
            )
        }
    }
}

// Question Screen

@Composable
fun QuestionScreen(
    gameState: GameState,
    onAnswer:  (Int) -> Unit,
    onCancel:  () -> Unit
) {
    val total = gameState.questions.size
    val index = gameState.currentIndex

    // out of bounds — render nothing rather than crash.
    if (index >= total || total == 0) return

    val q = gameState.questions[index]
    val progress = (index.toFloat() / total)

    var inputText by remember(index) { mutableStateOf("") }
    val userAnswer = inputText.toIntOrNull()
    val isValid    = userAnswer != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp, vertical = 24.dp)
    ) {
        // Top bar: scores + cancel
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Score chips
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ScoreChip(label = "✓", value = gameState.correctCount, bg = AccentMint.copy(alpha = 0.15f), fg = AccentMint)
                ScoreChip(label = "✗", value = gameState.wrongCount,   bg = AccentCoral.copy(alpha = 0.13f), fg = AccentCoral)
            }
            // Cancel
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(LightGray)
                    .clickable { onCancel() }
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Text("CANCEL", fontSize = 11.sp, fontWeight = FontWeight.Bold,
                    color = MidGray, letterSpacing = 1.5.sp)
            }
        }

        Spacer(Modifier.height(68.dp))

        // Question card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(28.dp))
                .background(InkBlack)
                .padding(vertical = 48.dp, horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("SOLVE FOR x", fontSize = 10.sp, fontWeight = FontWeight.Bold,
                    color = MidGray, letterSpacing = 2.5.sp)
                Spacer(Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    NumberBox(q.a.toString(), Cream)
                    Spacer(Modifier.width(12.dp))
                    Text("+", fontSize = 36.sp, fontWeight = FontWeight.Light, color = MidGray)
                    Spacer(Modifier.width(12.dp))
                    NumberBox(q.b.toString(), Cream)
                    Spacer(Modifier.width(12.dp))
                    Text("=", fontSize = 36.sp, fontWeight = FontWeight.Light, color = MidGray)
                    Spacer(Modifier.width(12.dp))
                    NumberBox("x", AccentCoral)
                }
            }
        }

        Spacer(Modifier.height(36.dp))

        // Answer input
        Text("YOUR ANSWER", fontSize = 10.sp, fontWeight = FontWeight.Bold,
            color = MidGray, letterSpacing = 2.sp)
        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = inputText,
            onValueChange = { if (it.length <= 4) inputText = it },
            placeholder = {
                Text("?", color = LightGray, fontSize = 44.sp, fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            },
            textStyle = TextStyle(
                fontSize = 48.sp, fontWeight = FontWeight.Black,
                color = InkBlack, textAlign = TextAlign.Center
            ),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor   = AccentCoral,
                unfocusedBorderColor = LightGray,
                cursorColor          = AccentCoral,
                focusedContainerColor   = Color.White,
                unfocusedContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Spacer(Modifier.weight(1f))

        // Next button
        Button(
            onClick = { userAnswer?.let { onAnswer(it) } },
            enabled = isValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor        = AccentCoral,
                disabledContainerColor = LightGray
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp
            )
        ) {
            val isLast = index == total - 1
            Text(
                if (isLast) "FINISH →" else "NEXT →",
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = if (isValid) Color.White else MidGray
            )
        }

        Spacer(Modifier.height(8.dp))
    }
}

// Result Screen

@Composable
fun ResultScreen(gameState: GameState, onPlayAgain: () -> Unit) {
    val total   = gameState.questions.size
    val correct = gameState.correctCount
    val wrong   = gameState.wrongCount
    val pct     = if (total > 0) (correct * 100f / total).toInt() else 0

    val message = when {
        pct == 100 -> "Perfect score!"
        pct >= 80  -> "Great work!"
        pct >= 60  -> "Not bad!"
        else       -> "Keep practicing!"
    }

    // Animated score counter
    val animatedPct by animateIntAsState(
        targetValue = pct,
        animationSpec = tween(1200, easing = EaseOutCubic),
        label = "pct"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))

        Text("RESULTS", fontSize = 11.sp, fontWeight = FontWeight.Bold,
            color = MidGray, letterSpacing = 3.sp)

        Spacer(Modifier.height(32.dp))

        // Big score circle
        Box(
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .background(InkBlack),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("$animatedPct%",
                    style = TextStyle(fontSize = 42.sp, fontWeight = FontWeight.Black, color = Cream))
            }
        }

        Spacer(Modifier.height(24.dp))

        Text(message, fontSize = 26.sp, fontWeight = FontWeight.Bold, color = InkBlack)
        Spacer(Modifier.height(4.dp))
        Text("You answered $total questions", fontSize = 14.sp, color = MidGray)

        Spacer(Modifier.height(40.dp))

        // Stats row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                label = "CORRECT",
                value = "$correct",
                color = AccentMint,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                label = "WRONG",
                value = "$wrong",
                color = AccentCoral,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.weight(1f))

        // Play again
        Button(
            onClick = onPlayAgain,
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = InkBlack),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text("PLAY AGAIN →", fontSize = 16.sp, fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp, color = SoftGold)
        }
    }
}

// Reusable Composables

@Composable
fun ScoreChip(label: String, value: Int, bg: Color, fg: Color) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(bg)
            .padding(horizontal = 12.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(label, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = fg)
        Text("$value", fontSize = 16.sp, fontWeight = FontWeight.Black, color = fg)
    }
}

@Composable
fun NumberBox(text: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(alpha = 0.12f))
            .padding(horizontal = 16.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                color = color
            )
        )
    }
}

@Composable
fun StatCard(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color.copy(alpha = 0.12f))
            .border(1.5.dp, color.copy(alpha = 0.25f), RoundedCornerShape(16.dp))
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(value, fontSize = 42.sp, fontWeight = FontWeight.Black, color = color)
            Spacer(Modifier.height(4.dp))
            Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold,
                color = color.copy(alpha = 0.7f), letterSpacing = 1.5.sp)
        }
    }
}


