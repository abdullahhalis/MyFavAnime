package com.abdullahhalis.myfavanime.di

import com.abdullahhalis.myfavanime.ui.screen.detail.DetailAnimeViewModel
import com.abdullahhalis.myfavanime.ui.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailAnimeViewModel(get())}
}