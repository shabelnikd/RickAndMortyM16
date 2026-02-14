package com.shabelnikd.character.domain.usecase

import com.shabelnikd.character.domain.model.Character
import com.shabelnikd.character.domain.repository.CharacterRepository
import com.shabelnikd.core.network.model.AppError
import com.shabelnikd.core.network.model.DataResult

class GetCharactersByIdsUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(charactersIds: List<Int>): DataResult<List<Character>, AppError> {
        return repository.getCharactersByIds(charactersIds)
    }
}