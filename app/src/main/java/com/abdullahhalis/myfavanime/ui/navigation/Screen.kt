package com.abdullahhalis.myfavanime.ui.navigation

sealed class Screen(val route: String){
    data object Home : Screen("home")
    data object Favorite : Screen("favorite")
    data object DetailAnime : Screen("home/{id}") {
        fun createRoute(id: Int) = "home/$id"
    }
}