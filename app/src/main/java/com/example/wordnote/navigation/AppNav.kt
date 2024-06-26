package com.example.wordnote.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wordnote.feature.about.AboutUsScreen
import com.example.wordnote.feature.search.SearchRepository
import com.example.wordnote.feature.search.SearchScreen
import com.example.wordnote.feature.search.SearchViewModel

@Composable
fun AppNav(navHostController: NavHostController, searchRepository: SearchRepository) {
    val searchViewModel = remember { SearchViewModel(searchRepository) }

    NavHost(
        navController = navHostController,
        startDestination = ScreenRoute.SearchPage.routeName
    ){
        composable(ScreenRoute.SearchPage.routeName) {
            SearchScreen(navHostController = navHostController, viewModel = searchViewModel)
        }
        composable(ScreenRoute.AboutPage.routeName) {
            AboutUsScreen(navHostController = navHostController)
        }
    }
}
