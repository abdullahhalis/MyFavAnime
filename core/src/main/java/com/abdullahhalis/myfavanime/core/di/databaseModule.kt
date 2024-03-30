package com.abdullahhalis.myfavanime.core.di

import androidx.room.Room
import com.abdullahhalis.myfavanime.core.data.source.local.room.AnimeDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<AnimeDatabase>().favAnimeDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("animelist".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            AnimeDatabase::class.java, "Anime.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}