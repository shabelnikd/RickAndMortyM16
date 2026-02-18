package com.shabelnikd.episode.domain.usecase

import com.shabelnikd.episode.domain.model.EpisodeResponse
import com.shabelnikd.episode.domain.repository.EpisodeRepository
import com.shabelnikd.core.network.model.AppError
import com.shabelnikd.core.network.model.DataResult

class GetEpisodesUseCase(
    private val repository: EpisodeRepository
) {
    suspend operator fun invoke(page: Int): DataResult<EpisodeResponse, AppError> {
        return repository.getEpisodes(page)
    }
}