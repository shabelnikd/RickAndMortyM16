package com.shabelnikd.rickandmorty.domain.usecase

import com.shabelnikd.rickandmorty.domain.model.Character
import com.shabelnikd.rickandmorty.domain.repository.CharacterRepository
import com.shabelnikd.rickandmorty.network.model.AppError
import com.shabelnikd.rickandmorty.network.model.DataResult

class GetCharacterByIdUseCase(
private val repository: CharacterRepository
) {
    suspend operator fun invoke(characterId: Int): DataResult<Character, AppError> {
        return repository.getCharacterById(characterId)
    }
}