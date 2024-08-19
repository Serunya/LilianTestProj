package com.tailspin.liliantestproj.data.source.remote.api

import com.tailspin.liliantestproj.data.source.remote.dto.CheckDTO
import com.tailspin.liliantestproj.data.source.remote.dto.CheckOperation

class CheckDtoApiMockImpl : CheckDtoApi {

    private val mockList = listOf(
        CheckDTO(0, 1685614507000L, CheckOperation.LOAD, "Комбайн: A456EP", 4450, false),
        CheckDTO(1, 1685614987000L, CheckOperation.LOAD, "Комбайн: B112MT", 3500, false),
        CheckDTO(2, 1685615107L, CheckOperation.LOAD, "Комбайн: C356PK", 4700, false),
        CheckDTO(3, 1685614507000L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 12500, false),
        CheckDTO(4, 1685616307L, CheckOperation.LOAD, "Комбайн: A456EP", 3700, false),
        CheckDTO(5, 1685616613L, CheckOperation.LOAD, "Комбайн: B112MT", 4100, false),
        CheckDTO(6, 1685617025L, CheckOperation.LOAD, "Комбайн: C356PK", 2500, false),
        CheckDTO(7, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(8, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(9, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(10, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(11, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(12, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(13, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(14, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(15, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(16, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(17, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(18, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(19, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(20, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(21, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(22, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(23, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(24, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(25, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(26, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(27, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(28, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(29, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(30, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(31, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(32, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(33, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(34, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(35, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(36, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(37, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(38, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(39, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(40, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(41, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(42, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(43, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(44, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(45, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(46, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(47, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(48, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(49, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(50, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
        CheckDTO(51, 1685617249L, CheckOperation.UNLOAD, "Зерновоз: E566KP", 10300, false),
    )

    override suspend fun getCheckDto(limit: Int, offset: Int): List<CheckDTO> {
        println("$limit $offset")
        val fromIndex = offset * limit
        var toIndex = fromIndex + limit
        if (fromIndex > mockList.size)
            return emptyList()
        if (toIndex > mockList.size)
            toIndex = mockList.size
        println("$fromIndex, $toIndex ${mockList.subList(fromIndex, toIndex).size}")
        return mockList.subList(fromIndex, toIndex)
    }

    override suspend fun getTotalItemCount(): Int {
        return mockList.size
    }


}