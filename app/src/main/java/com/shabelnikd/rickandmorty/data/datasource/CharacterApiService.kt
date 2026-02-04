package com.shabelnikd.rickandmorty.data.datasource

import com.shabelnikd.rickandmorty.data.mapper.toDomain
import com.shabelnikd.rickandmorty.data.model.CharacterRequest
import com.shabelnikd.rickandmorty.data.model.CharacterResponseDto
import com.shabelnikd.rickandmorty.domain.model.CharacterResponse
import com.shabelnikd.rickandmorty.network.model.AppError
import com.shabelnikd.rickandmorty.network.model.DataResult
import com.shabelnikd.rickandmorty.network.model.map
import com.shabelnikd.rickandmorty.network.util.safeGet
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers

class CharacterApiService(
    private val client: HttpClient
) {

    suspend fun getCharacters(request: CharacterRequest): DataResult<CharacterResponse, AppError> =

        client.safeGet<CharacterResponseDto>(url = "character", dispatcher = Dispatchers.IO) {
            url.parameters.apply {
                append("page", request.page.toString())
                request.query?.let { append("name", it) }
                request.status?.let { append("status", it) }
                request.species?.let { append("species", it) }
                request.gender?.let { append("gender", it) }
                request.type?.let { append("type", it) }
            }
        }.map {
           it.toDomain()
        }


}