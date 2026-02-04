package com.shabelnikd.rickandmorty.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.shabelnikd.rickandmorty.domain.model.Character
import com.shabelnikd.rickandmorty.domain.model.CharacterResponse
import com.shabelnikd.rickandmorty.network.model.AppError
import com.shabelnikd.rickandmorty.network.model.DataResult
import com.shabelnikd.rickandmorty.network.model.onError
import com.shabelnikd.rickandmorty.network.model.onSuccess

const val START_INDEX = 1

class CharacterPageSource(
    val result: DataResult<CharacterResponse, AppError>
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val currentKey = params.key ?: START_INDEX

        try {
            result.onSuccess { data ->
                return LoadResult.Page(
                    data = data.results.orEmpty(),
                    prevKey = data.info.prev.let { currentKey.minus(1) },
                    nextKey = data.info.next.let { currentKey.plus(1) }
                )
            }.onError { error ->
                return LoadResult.Error(throwable = Throwable("Error on Paging Source"))

            }

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
        return null
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}