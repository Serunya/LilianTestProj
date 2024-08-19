package com.tailspin.liliantestproj.data.source.remote.api

import com.tailspin.liliantestproj.data.source.remote.dto.CheckDTO


interface CheckDtoApi {

    suspend fun getCheckDto(limit : Int, offset: Int) : List<CheckDTO>
    suspend fun getTotalItemCount() : Int

}