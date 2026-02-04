package com.shabelnikd.rickandmorty.domain.model


data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)