package com.abdullahhalis.myfavanime

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abdullahhalis.myfavanime.ui.components.MyAppBar
import com.abdullahhalis.myfavanime.ui.navigation.Screen
import com.abdullahhalis.myfavanime.ui.screen.detail.DetailAnimeScreen
import com.abdullahhalis.myfavanime.ui.screen.home.HomeScreen

@Composable
fun MyFavAnimeApp(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val uri = Uri.parse("myfavanime://favorite")
    val context = LocalContext.current
    Scaffold(
        topBar = {
            when (currentRoute) {
                Screen.Home.route -> {
                    MyAppBar(title = stringResource(id = R.string.app_name), action = {
                        IconButton(onClick = {
                            context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                        }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Favorite",
                                tint = Color(0xFFF72798)
                            )
                        }
                    })
                }
                Screen.DetailAnime.route -> {
                    MyAppBar(title = "Detail Anime", navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    })
                }
            }
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = { id ->
                    navController.navigate(Screen.DetailAnime.createRoute(id))
                })
            }
            composable(
                route = Screen.DetailAnime.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                DetailAnimeScreen(id = id)
            }
        }
    }
}