package com.shabelnikd.episode.domain.model

data class Episode(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String, // S01E01
    val characters: List<String>,
    val url: String, // https://rickandmortyapi.com/api/episode/1
    val created: String // 2017-11-10T12:56:33.798Z
)
