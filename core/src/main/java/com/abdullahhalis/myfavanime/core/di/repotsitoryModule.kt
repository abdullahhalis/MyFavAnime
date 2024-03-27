package com.abdullahhalis.myfavanime.core.di

import com.abdullahhalis.myfavanime.core.data.AnimeRepository
import com.abdullahhalis.myfavanime.core.data.source.local.LocalDataSource
import com.abdullahhalis.myfavanime.core.data.source.remote.RemoteDataSource
import com.abdullahhalis.myfavanime.core.domain.repository.IAnimeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IAnimeRepository> {
        AnimeRepository(
            get(),
            get()
        )
    }
}