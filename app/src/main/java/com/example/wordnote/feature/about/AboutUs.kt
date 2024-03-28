package com.example.wordnote.feature.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wordnote.R
import com.example.wordnote.components.bottomBar.BottomBarView
import com.example.wordnote.ui.theme.Blue100
import com.example.wordnote.ui.theme.Blue200
import com.example.wordnote.ui.theme.Blue300
import com.example.wordnote.ui.theme.Blue50

@Composable
fun AboutUsScreen(navHostController: NavHostController) {
    Scaffold(
        bottomBar = { BottomBarView(navHostController) }
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.onBackground)
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(380.dp)
                    .height(240.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colors.secondary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.about_us_text),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}