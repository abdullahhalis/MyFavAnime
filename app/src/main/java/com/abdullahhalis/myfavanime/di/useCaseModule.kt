package com.abdullahhalis.myfavanime.di

import com.abdullahhalis.myfavanime.core.domain.usecase.AnimeInteractor
import com.abdullahhalis.myfavanime.core.domain.usecase.AnimeUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<AnimeUseCase> { AnimeInteractor(get()) }
}