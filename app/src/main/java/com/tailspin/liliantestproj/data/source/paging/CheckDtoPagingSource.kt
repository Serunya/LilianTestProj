package com.tailspin.liliantestproj.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tailspin.liliantestproj.data.source.remote.api.CheckDtoApi
import com.tailspin.liliantestproj.data.source.remote.dto.CheckDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CheckDtoPagingSource(private val checkDtoApi: CheckDtoApi) :
    PagingSource<Int, CheckDTO>() {
    override fun getRefreshKey(state: PagingState<Int, CheckDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CheckDTO> =
        withContext(Dispatchers.IO) {
            val position = params.key ?: 0
            return@withContext try {
                val result = checkDtoApi.getCheckDto(params.loadSize, position)
                LoadResult.Page(
                    data = result,
                    prevKey = if (position == 0) null else -1,
                    nextKey = if (result.isEmpty()) null else position + 1
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
}