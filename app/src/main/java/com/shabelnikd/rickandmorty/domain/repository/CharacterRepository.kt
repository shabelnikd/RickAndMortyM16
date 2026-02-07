package com.shabelnikd.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.shabelnikd.rickandmorty.domain.model.Character
import com.shabelnikd.rickandmorty.domain.model.CharacterParams
import com.shabelnikd.rickandmorty.domain.model.CharacterResponse
import com.shabelnikd.rickandmorty.domain.model.Info
import com.shabelnikd.rickandmorty.network.model.AppError
import com.shabelnikd.rickandmorty.network.model.DataResult
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharactersPageSource(params: CharacterParams): Flow<PagingData<Character>>

    suspend fun getCharacters(params: CharacterParams): DataResult<CharacterResponse, AppError>

    suspend fun getCharacterById(characterId: Int): DataResult<Character, AppError>

    suspend fun getCharactersByIds(characterIds: List<Int>): DataResult<List<Character>, AppError>

    suspend fun getCharactersTotalInfo(): DataResult<Info, AppError>
}