package com.abdullahhalis.myfavanime.core.di

import androidx.room.Room
import com.abdullahhalis.myfavanime.core.data.source.local.room.AnimeDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<AnimeDatabase>().favAnimeDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AnimeDatabase::class.java, "Anime.db"
        ).fallbackToDestructiveMigration().build()
    }
}