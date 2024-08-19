package com.tailspin.liliantestproj.domain.repository

import androidx.paging.PagingData
import com.tailspin.liliantestproj.data.source.remote.dto.CheckDTO
import kotlinx.coroutines.flow.Flow

interface CheckDtoRepository {

    fun getCheckData(): Flow<PagingData<CheckDTO>>
    fun getTotalItemCount(): Flow<Int>
}