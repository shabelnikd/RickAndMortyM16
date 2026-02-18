package com.shabelnikd.episode.domain.usecase

import com.shabelnikd.episode.domain.model.Episode
import com.shabelnikd.episode.domain.repository.EpisodeRepository
import com.shabelnikd.core.network.model.AppError
import com.shabelnikd.core.network.model.DataResult

class GetEpisodesByIdsUseCase(
    private val repository: EpisodeRepository
) {
    suspend operator fun invoke(episodeIds: List<Int>): DataResult<List<Episode>, AppError> {
        return repository.getEpisodesByIds(episodeIds)
    }
}