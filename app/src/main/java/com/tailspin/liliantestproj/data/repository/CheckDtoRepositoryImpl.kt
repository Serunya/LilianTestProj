package com.tailspin.liliantestproj.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tailspin.liliantestproj.data.source.paging.CheckDtoPagingSource
import com.tailspin.liliantestproj.data.source.remote.api.CheckDtoApi
import com.tailspin.liliantestproj.data.source.remote.dto.CheckDTO
import com.tailspin.liliantestproj.domain.repository.CheckDtoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckDtoRepositoryImpl(private val checkDtoApi: CheckDtoApi) : CheckDtoRepository {

    companion object {
        const val PAGING_SIZE = 20
    }

    override fun getCheckData(): Flow<PagingData<CheckDTO>> = Pager(
        config = PagingConfig(PAGING_SIZE, initialLoadSize = PAGING_SIZE),
        pagingSourceFactory = { CheckDtoPagingSource(checkDtoApi) }
    ).flow

    override fun getTotalItemCount(): Flow<Int> = flow {
        emit(checkDtoApi.getTotalItemCount())
    }

}