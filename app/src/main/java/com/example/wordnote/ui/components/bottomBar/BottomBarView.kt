package com.example.wordnote.ui.components.bottomBar

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.wordnote.ui.navigation.ScreenRoute

@Composable
fun BottomBarView(navHostController: NavHostController){
    val barItems = listOf(
        ScreenRoute.SearchPage,
        ScreenRoute.AboutPage
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomAppBar() {
        barItems.forEach {
            BottomNavigationItem(
                label = { Text(text = it.title)},
                icon = { Icon(it.icon, contentDescription = "")},
                selected = currentRoute == it.routeName,
                onClick = {
                    navHostController.navigate(it.routeName){
                        popUpTo(navHostController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }

    }
}

