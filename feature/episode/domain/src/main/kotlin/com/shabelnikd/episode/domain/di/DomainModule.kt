package com.shabelnikd.episode.domain.di

import com.shabelnikd.episode.domain.usecase.GetEpisodeByIdUseCase
import com.shabelnikd.episode.domain.usecase.GetEpisodePageSourceUseCase
import com.shabelnikd.episode.domain.usecase.GetEpisodesByIdsUseCase
import com.shabelnikd.episode.domain.usecase.GetEpisodesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val episodeDomainModule = module {
    factoryOf(::GetEpisodesUseCase)
    factoryOf(::GetEpisodeByIdUseCase)
    factoryOf(::GetEpisodesByIdsUseCase)
    factoryOf(::GetEpisodePageSourceUseCase)
}