package com.shabelnikd.core.network.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shabelnikd.core.network.model.AppError
import com.shabelnikd.core.network.model.DataResult

interface PagingResponse<T> {
    val results: List<T>
    val pages: Int
}

abstract class BasePageSource<Value : Any, DTO : PagingResponse<Value>> : PagingSource<Int, Value>() {

    abstract suspend fun fetchData(page: Int): DataResult<DTO, AppError>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        val page = params.key ?: 1

        return when (val result = fetchData(page)) {
            is DataResult.Success -> {
                val items = result.data.results
                val totalPages = result.data.pages

                LoadResult.Page(
                    data = items,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (items.isEmpty() || page >= totalPages) null else page + 1
                )
            }
            is DataResult.Error -> {
                LoadResult.Error(result.error.asThrowable())
            }
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}