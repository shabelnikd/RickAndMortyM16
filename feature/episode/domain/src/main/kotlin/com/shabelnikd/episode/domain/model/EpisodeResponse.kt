package com.shabelnikd.episode.domain.model

import com.shabelnikd.core.network.util.PagingResponse

data class EpisodeResponse(
    val info: Info,
    override val results: List<Episode>
) : PagingResponse<Episode> {
    override val pages: Int get() = info.pages
}