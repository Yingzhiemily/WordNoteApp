package com.example.wordnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.wordnote.feature.search.ApiClient
import com.example.wordnote.feature.search.SearchRepository
import com.example.wordnote.navigation.AppNav
import com.example.wordnote.ui.theme.WordNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val searchRepository = SearchRepository(ApiClient.searchApi)
        setContent {
            WordNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNav(navHostController = rememberNavController(), searchRepository = searchRepository)
                }
            }
        }
    }
}
