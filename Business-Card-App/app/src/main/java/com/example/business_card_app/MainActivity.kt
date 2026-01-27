package com.example.business_card_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.business_card_app.ui.theme.BusinessCardAppTheme
import com.example.business_card_app.ui.theme.DarkBlue
import com.example.business_card_app.ui.theme.DarkGreen
import com.example.business_card_app.ui.theme.LightGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LightGreen
                ) {
                    BusinessCardScreen()
                }
            }
        }
    }
}

@Composable
fun BusinessCardScreen() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(LightGreen)
    ) {

        LogoNameTitleScreen(
            modifier = Modifier.align(Alignment.Center)
        )

        ComposeContactInfoCard(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


@Composable
fun LogoNameTitleScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image = painterResource(R.drawable.android_logo)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .background(DarkBlue)
        )
        Text(
            text = stringResource(R.string.firstname_lastname),
            fontSize = 41.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(R.string.job_title),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = DarkGreen
        )
    }
}


@Composable
fun ComposeContactInfoCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContactInfoCard(
            logo = painterResource(R.drawable.phone),
            detail = stringResource(R.string.contact_number)
        )

        ContactInfoCard(
            logo = painterResource(R.drawable.share),
            detail = stringResource(R.string.share_username)
        )

        ContactInfoCard(
            logo = painterResource(R.drawable.mail),
            detail = stringResource(R.string.email)
        )
    }
}


@Composable
fun ContactInfoCard(logo: Painter, detail: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = logo,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(DarkGreen)
        )
        Text(
            text = detail,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 16.dp)
                .width(150.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = LightGreen
        ) {
            BusinessCardScreen()
        }
    }
}