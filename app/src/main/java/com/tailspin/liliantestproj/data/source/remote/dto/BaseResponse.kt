package com.tailspin.liliantestproj.data.source.remote.dto

sealed interface BaseResponse<out T> {
    class Success<out T>(val data: T) : BaseResponse<T>
    data object Loading : BaseResponse<Nothing>
    class Error(val throwable: Throwable) : BaseResponse<Nothing>
}