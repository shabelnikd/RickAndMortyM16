package com.shabelnikd.character.domain.usecase

import com.shabelnikd.character.domain.model.Character
import com.shabelnikd.character.domain.repository.CharacterRepository
import com.shabelnikd.core.network.model.AppError
import com.shabelnikd.core.network.model.DataResult

class GetCharacterByIdUseCase(
private val repository: CharacterRepository
) {
    suspend operator fun invoke(characterId: Int): DataResult<Character, AppError> {
        return repository.getCharacterById(characterId)
    }
}