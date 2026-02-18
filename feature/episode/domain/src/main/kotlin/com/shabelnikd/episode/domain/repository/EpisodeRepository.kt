package com.shabelnikd.episode.domain.repository

import androidx.paging.PagingData
import com.shabelnikd.episode.domain.model.Episode
import com.shabelnikd.episode.domain.model.EpisodeResponse
import com.shabelnikd.core.network.model.AppError
import com.shabelnikd.core.network.model.DataResult
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getEpisodePageSource(page: Int): Flow<PagingData<Episode>>

    suspend fun getEpisodes(page: Int): DataResult<EpisodeResponse, AppError>

    suspend fun getEpisodeById(episodeId: Int): DataResult<Episode, AppError>

    suspend fun getEpisodesByIds(episodeIds: List<Int>): DataResult<List<Episode>, AppError>
}