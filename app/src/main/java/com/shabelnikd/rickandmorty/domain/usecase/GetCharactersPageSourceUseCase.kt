package com.shabelnikd.rickandmorty.domain.usecase

import androidx.paging.PagingData
import com.shabelnikd.rickandmorty.domain.model.Character
import com.shabelnikd.rickandmorty.domain.model.CharacterParams
import com.shabelnikd.rickandmorty.domain.repository.CharacterRepository
import com.shabelnikd.rickandmorty.network.model.AppError
import com.shabelnikd.rickandmorty.network.model.DataResult
import kotlinx.coroutines.flow.Flow

class GetCharactersPageSourceUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(params: CharacterParams): Flow<PagingData<Character>> {
        return repository.getCharactersPageSource(params)
    }
}