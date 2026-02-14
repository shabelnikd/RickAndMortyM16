package com.shabelnikd.rickandmorty

import android.app.Application
import com.shabelnikd.character.data.di.dataModule
import com.shabelnikd.character.domain.di.domainModule
import com.shabelnikd.character.ui.di.uiModule
import com.shabelnikd.core.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(level = Level.DEBUG)

            modules(
                dataModule, domainModule, networkModule, uiModule
            )
        }
    }
}