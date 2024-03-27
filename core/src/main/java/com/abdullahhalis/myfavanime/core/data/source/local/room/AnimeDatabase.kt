package com.abdullahhalis.myfavanime.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdullahhalis.myfavanime.core.data.source.local.entity.FavoriteAnimeEntity

@Database(entities = [FavoriteAnimeEntity::class], version = 1, exportSchema = false)
abstract class AnimeDatabase: RoomDatabase() {
    abstract fun favAnimeDao(): FavAnimeDao
}