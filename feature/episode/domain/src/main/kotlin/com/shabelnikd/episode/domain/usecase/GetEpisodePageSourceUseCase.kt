package com.shabelnikd.episode.domain.usecase

import androidx.paging.PagingData
import com.shabelnikd.episode.domain.model.Episode
import com.shabelnikd.episode.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow

class GetEpisodePageSourceUseCase(
    private val repository: EpisodeRepository
) {
    operator fun invoke(page: Int): Flow<PagingData<Episode>> {
        return repository.getEpisodePageSource(page)
    }
}