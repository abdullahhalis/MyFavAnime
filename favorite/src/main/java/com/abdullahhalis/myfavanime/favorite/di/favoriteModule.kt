package com.abdullahhalis.myfavanime.favorite.di

import com.abdullahhalis.myfavanime.favorite.screen.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel{ FavoriteViewModel(get())}
}