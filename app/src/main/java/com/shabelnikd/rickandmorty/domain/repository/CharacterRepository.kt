package com.shabelnikd.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.shabelnikd.rickandmorty.domain.model.Character
import com.shabelnikd.rickandmorty.domain.model.CharacterParams
import com.shabelnikd.rickandmorty.domain.model.Info
import com.shabelnikd.rickandmorty.network.model.AppError
import com.shabelnikd.rickandmorty.network.model.DataResult
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters(params: CharacterParams): Flow<PagingData<Character>>

    suspend fun getCharacterById(characterId: Int): DataResult<Character, AppError>

    suspend fun getCharactersTotalInfo(): DataResult<Info, AppError>

}