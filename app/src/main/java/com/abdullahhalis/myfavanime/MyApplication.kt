package com.abdullahhalis.myfavanime

import android.app.Application
import com.abdullahhalis.myfavanime.core.di.databaseModule
import com.abdullahhalis.myfavanime.core.di.networkModule
import com.abdullahhalis.myfavanime.core.di.repositoryModule
import com.abdullahhalis.myfavanime.di.useCaseModule
import com.abdullahhalis.myfavanime.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModules
                )
            )
        }
    }
}