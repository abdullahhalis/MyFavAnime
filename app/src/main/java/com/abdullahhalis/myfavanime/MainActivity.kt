package com.abdullahhalis.myfavanime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.abdullahhalis.myfavanime.ui.theme.MyFavAnimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFavAnimeTheme {
                MyFavAnimeApp()
            }
        }
    }
}
