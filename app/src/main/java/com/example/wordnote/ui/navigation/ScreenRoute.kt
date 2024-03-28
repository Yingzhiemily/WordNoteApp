package com.example.wordnote.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenRoute(val routeName: String, val title: String,val icon: ImageVector) {
    object SearchPage: ScreenRoute("search_page","Home",Icons.Default.Search)
    object AboutPage: ScreenRoute("about_page","About Us",Icons.Default.Favorite)
}