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
        setContent {
            ArtSpaceAppTheme {
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
    val currentArtwork = artworks[currentIndex]

    ArtSpaceScreen(
        artwork = currentArtwork,
        currentIndex = currentIndex,
        totalCount = artworks.size,
        onPreviousClick = {
            currentIndex = Math.floorMod(currentIndex - 1, artworks.size)
        },
        onNextClick = {
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
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        ArtworkWall(artwork = artwork)

        ArtworkDescriptor(
            artwork = artwork,
            currentIndex = currentIndex,
            totalCount = totalCount
        )

        DisplayController(
            onPreviousClick = onPreviousClick,
            onNextClick = onNextClick
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ArtworkWall(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(3f / 4f)
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
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = artwork.imageRes),
                contentDescription = stringResource(id = artwork.titleRes),
                modifier = Modifier.fillMaxSize(),
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
        Text(
            text = stringResource(id = artwork.titleRes),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        Spacer(modifier = Modifier.height(4.dp))

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
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedButton(
            onClick = onPreviousClick,
            modifier = Modifier.weight(1f)
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
            totalCount = 3,
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}