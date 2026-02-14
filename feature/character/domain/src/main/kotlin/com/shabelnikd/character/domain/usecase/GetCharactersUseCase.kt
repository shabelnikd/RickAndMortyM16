package com.shabelnikd.character.domain.usecase

import com.shabelnikd.character.domain.model.CharacterParams
import com.shabelnikd.character.domain.model.CharacterResponse
import com.shabelnikd.character.domain.repository.CharacterRepository
import com.shabelnikd.core.network.model.AppError
import com.shabelnikd.core.network.model.DataResult

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(params: CharacterParams): DataResult<CharacterResponse, AppError> {
        return repository.getCharacters(params)
    }
}