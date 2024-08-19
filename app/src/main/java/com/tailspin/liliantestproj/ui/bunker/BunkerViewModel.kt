package com.tailspin.liliantestproj.ui.bunker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tailspin.liliantestproj.data.repository.CheckDtoRepositoryImpl
import com.tailspin.liliantestproj.data.source.remote.api.CheckDtoApiMockImpl
import com.tailspin.liliantestproj.domain.usecase.GetCheckDtoUseCase


class BunkerViewModel(private val getCheckDtoUseCase: GetCheckDtoUseCase) : ViewModel() {
    fun getCheckDto() = getCheckDtoUseCase.getCheckDto().cachedIn(viewModelScope)
    var weightBunker by mutableIntStateOf(23900)
    var weightMemory by mutableIntStateOf(700)
    var weightLoaded by mutableIntStateOf(23900)
    var checkCount = getCheckDtoUseCase.getTotalItemCount()

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(BunkerViewModel::class.java)) {
                    return BunkerViewModel(
                        GetCheckDtoUseCase(
                            CheckDtoRepositoryImpl(
                                CheckDtoApiMockImpl()
                            )
                        )
                    ) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}