package com.example.watchthesemoviestest

import android.app.Application
import com.example.watchthesemoviestest.di.Module.moduleDI
import com.example.watchthesemoviestest.di.Module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MovieApplication: Application() {

    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            modules(listOf(moduleDI, networkModule))
        }
    }
}