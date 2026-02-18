package com.shabelnikd.episode.domain.usecase

import com.shabelnikd.episode.domain.model.Episode
import com.shabelnikd.episode.domain.repository.EpisodeRepository
import com.shabelnikd.core.network.model.AppError
import com.shabelnikd.core.network.model.DataResult

class GetEpisodeByIdUseCase(
private val repository: EpisodeRepository
) {
    suspend operator fun invoke(episodeId: Int): DataResult<Episode, AppError> {
        return repository.getEpisodeById(episodeId)
    }
}