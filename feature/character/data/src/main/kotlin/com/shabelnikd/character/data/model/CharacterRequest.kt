package com.shabelnikd.character.data.model

data class CharacterRequest(
    val page: Int,
    val query: String?,
    val status: String?,
    val gender: String?,
    val species: String?,
    val type: String?,
)