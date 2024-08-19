package com.tailspin.liliantestproj.domain.usecase

import com.tailspin.liliantestproj.domain.repository.CheckDtoRepository

class GetCheckDtoUseCase(private val checkDtoRepository: CheckDtoRepository) {
    fun getCheckDto() = checkDtoRepository.getCheckData()
    fun getTotalItemCount() = checkDtoRepository.getTotalItemCount()
}