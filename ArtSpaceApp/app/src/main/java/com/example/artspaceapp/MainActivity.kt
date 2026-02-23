package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme


data class Artwork(
    @DrawableRes val imageRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val artistRes: Int,
    @StringRes val yearRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                // Surface is the Material Design root container.
                // It handles background color, elevation, and shapes consistently.
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val artworks = remember {
        listOf(
            Artwork(
                imageRes = R.drawable.artwork_1,
                titleRes = R.string.artwork_1_title,
                artistRes = R.string.artwork_1_artist,
                yearRes = R.string.artwork_1_year
            ),
            Artwork(
                imageRes = R.drawable.artwork_2,
                titleRes = R.string.artwork_2_title,
                artistRes = R.string.artwork_2_artist,
                yearRes = R.string.artwork_2_year
            ),
            Artwork(
                imageRes = R.drawable.artwork_3,
                titleRes = R.string.artwork_3_title,
                artistRes = R.string.artwork_3_artist,
                yearRes = R.string.artwork_3_year
            )
        )
    }

    var currentIndex by remember { mutableIntStateOf(0) }

    // Compute the current artwork from the list using the index
    val currentArtwork = artworks[currentIndex]

    // Pass state DOWN to the screen, and lambda callbacks UP for events
    ArtSpaceScreen(
        artwork = currentArtwork,
        currentIndex = currentIndex,
        totalCount = artworks.size,
        onPreviousClick = {
            // Wrap-around logic: go to last item if at the beginning
            // Best Practice: Use floorMod for safe negative modulo in Kotlin
            currentIndex = Math.floorMod(currentIndex - 1, artworks.size)
        },
        onNextClick = {
            // Wrap-around logic: go to first item if at the end
            currentIndex = (currentIndex + 1) % artworks.size
        }
    )
}

@Composable
fun ArtSpaceScreen(
    artwork: Artwork,
    currentIndex: Int,
    totalCount: Int,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween  // Push content + buttons to opposite ends
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        // ── Artwork Wall (Image + Frame) ──────────────────────────────────────
        ArtworkWall(artwork = artwork)

        // ── Artwork Description Card ──────────────────────────────────────────
        ArtworkDescriptor(
            artwork = artwork,
            currentIndex = currentIndex,
            totalCount = totalCount
        )

        // ── Navigation Buttons ────────────────────────────────────────────────
        DisplayController(
            onPreviousClick = onPreviousClick,
            onNextClick = onNextClick
        )

        // Bottom spacer for breathing room above navigation bar
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ArtworkWall(
    artwork: Artwork,
    modifier: Modifier = Modifier  // Best Practice: Always add a modifier parameter for flexibility
) {
    // Card provides Material Design elevation + rounded corners (the "frame" effect)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(3f / 4f)  // Maintains consistent 3:4 portrait ratio regardless of screen size
            .padding(horizontal = 8.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),  // Inner padding creates the "frame" border effect
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = artwork.imageRes),
                // Best Practice: ALWAYS provide a meaningful contentDescription for accessibility.
                // Screen readers (TalkBack) use this to describe images to visually impaired users.
                contentDescription = stringResource(id = artwork.titleRes),
                modifier = Modifier.fillMaxSize(),
                // ContentScale.Fit ensures the entire image is visible without cropping
                // Use ContentScale.Crop if you want to fill the space (may crop edges)
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun ArtworkDescriptor(
    artwork: Artwork,
    currentIndex: Int,
    totalCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Artwork title
        Text(
            text = stringResource(id = artwork.titleRes),
            // Best Practice: Use MaterialTheme.typography for consistent, scalable text styles.
            // These styles automatically adapt for different device font size settings.
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Artist name + year on the same row
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = artwork.artistRes),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "  (${stringResource(id = artwork.yearRes)})",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Position counter: "2 of 5"
        Text(
            text = "${currentIndex + 1} of $totalCount",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
            fontSize = 12.sp
        )
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,  // Push buttons to opposite sides
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Previous button
        // Best Practice: Use OutlinedButton for secondary actions, Button for primary actions
        OutlinedButton(
            onClick = onPreviousClick,
            modifier = Modifier.weight(1f)  // weight(1f) makes both buttons share space equally
        ) {
            Text(
                text = stringResource(R.string.button_previous),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Next button
        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.button_next),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true, name = "Art Space Screen - Light")
@Composable
fun ArtSpaceScreenPreview() {
    ArtSpaceAppTheme {
        ArtSpaceScreen(
            artwork = Artwork(
                imageRes = R.drawable.artwork_1,
                titleRes = R.string.artwork_1_title,
                artistRes = R.string.artwork_1_artist,
                yearRes = R.string.artwork_1_year
            ),
            currentIndex = 0,
            totalCount = 5,
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}