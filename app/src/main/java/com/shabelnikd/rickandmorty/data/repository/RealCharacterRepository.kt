package com.shabelnikd.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shabelnikd.rickandmorty.data.datasource.CharacterPageSource
import com.shabelnikd.rickandmorty.data.mapper.toDomain
import com.shabelnikd.rickandmorty.data.model.CharacterDto
import com.shabelnikd.rickandmorty.data.model.CharacterResponseDto
import com.shabelnikd.rickandmorty.domain.model.Character
import com.shabelnikd.rickandmorty.domain.model.CharacterParams
import com.shabelnikd.rickandmorty.domain.model.CharacterResponse
import com.shabelnikd.rickandmorty.domain.model.Info
import com.shabelnikd.rickandmorty.domain.repository.CharacterRepository
import com.shabelnikd.rickandmorty.network.model.AppError
import com.shabelnikd.rickandmorty.network.model.DataResult
import com.shabelnikd.rickandmorty.network.model.map
import com.shabelnikd.rickandmorty.network.util.safeGet
import io.ktor.client.HttpClient
import io.ktor.utils.io.ioDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class RealCharacterRepository(
    private val client: HttpClient
) : CharacterRepository {

    override fun getCharactersPageSource(params: CharacterParams): Flow<PagingData<Character>> =
        Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                CharacterPageSource(requestParams = params) { params ->
                    getCharacters(params)
                }
            }
        ).flow.flowOn(Dispatchers.IO)

    override suspend fun getCharacters(params: CharacterParams): DataResult<CharacterResponse, AppError> =
        client.safeGet<CharacterResponseDto>(url = "character", dispatcher = Dispatchers.IO) {
            url.parameters.apply {
                append("page", params.page.toString())
                params.query?.let { append("name", it) }
                params.status?.let { append("status", it) }
                params.species?.let { append("species", it) }
                params.gender?.let { append("gender", it) }
                params.type?.let { append("type", it) }
            }
        }.map {
            it.toDomain()
        }


    override suspend fun getCharacterById(characterId: Int): DataResult<Character, AppError> =
        client.safeGet<CharacterDto>(url = "character/${characterId}", dispatcher = ioDispatcher())
            .map { it.toDomain() }


    override suspend fun getCharactersByIds(characterIds: List<Int>): DataResult<List<Character>, AppError> =
        client.safeGet<List<CharacterDto>>(url = "character/${
            characterIds.joinToString(separator = ",", prefix = "[", postfix = "]")
        }",
            dispatcher = ioDispatcher()).map { result -> result.map { it.toDomain() } }


    override suspend fun getCharactersTotalInfo(): DataResult<Info, AppError> =
        client.safeGet<CharacterResponseDto>(url = "character?page=1", dispatcher = Dispatchers.IO)
            .map { it.info?.toDomain()
                ?: throw Exception("Не удалось получить информацию о персонажах") }
}