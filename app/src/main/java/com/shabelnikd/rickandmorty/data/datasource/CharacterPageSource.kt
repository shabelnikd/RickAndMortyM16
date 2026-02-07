package com.shabelnikd.rickandmorty.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shabelnikd.rickandmorty.domain.model.Character
import com.shabelnikd.rickandmorty.domain.model.CharacterParams
import com.shabelnikd.rickandmorty.domain.model.CharacterResponse
import com.shabelnikd.rickandmorty.network.model.AppError
import com.shabelnikd.rickandmorty.network.model.DataResult

const val START_INDEX = 1

class CharacterPageSource(
    private val requestParams: CharacterParams,
    private val request: suspend (CharacterParams) -> DataResult<CharacterResponse, AppError>,
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: START_INDEX

        return when (val result = request(requestParams.copy(page = page))) {
            is DataResult.Success -> {
                val characters = result.data.results

                LoadResult.Page(
                    data = characters,
                    prevKey = if (page == START_INDEX) null else page - 1,
                    nextKey = if (characters.isEmpty() || result.data.info.next == null) null else page + 1
                )
            }
            is DataResult.Error -> {
                val throwable = if (result.error is AppError.Api.TooManyRequests) {
                    result.error.copy(message = "Ты плохой и больше данных мы тебе не дадим").asThrowable()
                } else {
                    result.error.asThrowable()
                }
                LoadResult.Error(throwable)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}