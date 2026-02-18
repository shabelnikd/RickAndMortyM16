package com.shabelnikd.episode.data.mapper

import com.shabelnikd.episode.data.model.EpisodeDto
import com.shabelnikd.episode.data.model.EpisodeReponseDto
import com.shabelnikd.episode.data.model.InfoDto
import com.shabelnikd.episode.domain.model.Episode
import com.shabelnikd.episode.domain.model.EpisodeResponse
import com.shabelnikd.episode.domain.model.Info


fun EpisodeReponseDto.toDomain(): EpisodeResponse {
    return EpisodeResponse(
        info = this.info?.toDomain() ?: Info(count = -1, pages = -1, next = "", prev = ""),
        results = this.results?.map {
            it.toDomain()
        } ?: emptyList()
    )
}


fun EpisodeDto.toDomain(): Episode {
    return Episode(
        id = this.id ?: -1,
        name = this.name ?: "",
        airDate = this.airDate ?: "",
        episode = this.episode ?: "",
        characters = this.characters ?: emptyList(),
        url = this.url ?: "",
        created = this.created ?: ""
    )
}

fun InfoDto.toDomain(): Info {
    return Info(
        count = this.count ?: 0,
        pages = this.pages ?: 0,
        next = this.next ?: "",
        prev = this.prev ?: ""
    )
}
