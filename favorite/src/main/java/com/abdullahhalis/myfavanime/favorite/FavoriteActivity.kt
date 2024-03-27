package com.abdullahhalis.myfavanime.favorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abdullahhalis.myfavanime.MainActivity
import com.abdullahhalis.myfavanime.favorite.di.favoriteModule
import com.abdullahhalis.myfavanime.favorite.screen.FavoriteScreen
import com.abdullahhalis.myfavanime.ui.components.MyAppBar
import com.abdullahhalis.myfavanime.ui.navigation.Screen
import com.abdullahhalis.myfavanime.ui.screen.detail.DetailAnimeScreen
import com.abdullahhalis.myfavanime.ui.theme.MyFavAnimeTheme
import org.koin.core.context.loadKoinModules

class FavoriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(favoriteModule)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            MyFavAnimeTheme {
                Scaffold(
                    topBar = {
                        when (currentRoute) {
                            Screen.Favorite.route -> {
                                MyAppBar(title = "Favorite Anime", navigationIcon = {
                                    IconButton(onClick = {
                                        startActivity(
                                            Intent(
                                                this@FavoriteActivity,
                                                MainActivity::class.java
                                            )
                                        )
                                        finish()
                                    }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back",
                                            tint = Color.White
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
                        startDestination = Screen.Favorite.route,
                        modifier = Modifier.padding(contentPadding)
                    ) {
                        composable(route = Screen.Favorite.route) {
                            FavoriteScreen(navigateToDetail = { id ->
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
        }
    }
}