package com.tailspin.liliantestproj.data.source.remote.dto

data class CheckDTO(
    val uuid: Int,
    val operationDataTime: Long,
    val checkOperationType: CheckOperation,
    val vehicleName: String,
    val operationWeight: Int,
    val hasPrinted: Boolean,
)