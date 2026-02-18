package com.shabelnikd.character.domain.model

import com.shabelnikd.core.network.util.PagingResponse


data class CharacterResponse(
    val info: Info,
    override val results: List<Character>
) : PagingResponse<Character> {
    override val pages: Int = this.info.pages
}