package com.shabelnikd.episode.ui.di

import com.shabelnikd.episode.ui.episodes.EpisodeListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val episodeUiModule = module {
    viewModelOf(::EpisodeListViewModel)
}