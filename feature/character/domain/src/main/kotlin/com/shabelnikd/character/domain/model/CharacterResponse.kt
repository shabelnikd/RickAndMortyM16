package com.shabelnikd.character.domain.model


data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)