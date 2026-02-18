package com.shabelnikd.episode.data.di

import com.shabelnikd.episode.data.repository.RealEpisodeRepository
import com.shabelnikd.episode.domain.repository.EpisodeRepository
import org.koin.dsl.module

val episodeDataModule = module {
    single<EpisodeRepository> { RealEpisodeRepository(get()) }
}