package com.shabelnikd.character.domain.usecase

import androidx.paging.PagingData
import com.shabelnikd.character.domain.model.Character
import com.shabelnikd.character.domain.model.CharacterParams
import com.shabelnikd.character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersPageSourceUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(params: CharacterParams): Flow<PagingData<Character>> {
        return repository.getCharactersPageSource(params)
    }
}