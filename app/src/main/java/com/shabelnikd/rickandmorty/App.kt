package com.shabelnikd.rickandmorty

import android.app.Application
import com.shabelnikd.character.data.di.characterDataModule
import com.shabelnikd.character.domain.di.characterDomainModule
import com.shabelnikd.character.ui.di.characterUiModule
import com.shabelnikd.episode.ui.di.episodeUiModule
import com.shabelnikd.core.network.di.networkModule
import com.shabelnikd.episode.data.di.episodeDataModule
import com.shabelnikd.episode.domain.di.episodeDomainModule
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
                characterDataModule, characterDomainModule, networkModule, characterUiModule,
                episodeDataModule, episodeDomainModule, episodeUiModule
            )
        }
    }
}