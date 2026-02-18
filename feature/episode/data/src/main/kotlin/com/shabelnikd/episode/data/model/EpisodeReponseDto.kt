package com.shabelnikd.episode.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeReponseDto(
    @SerialName("info")
    val info: InfoDto?,
    @SerialName("results")
    val results: List<EpisodeDto>?
)