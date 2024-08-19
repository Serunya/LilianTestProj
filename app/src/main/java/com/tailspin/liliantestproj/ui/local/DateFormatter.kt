package com.tailspin.liliantestproj.ui.local

import androidx.compose.runtime.staticCompositionLocalOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateFormatter(private val pattern: String = "dd.MM.yyyy HH:mm:ss") {
    private val formatter = SimpleDateFormat(pattern, Locale.getDefault())

    fun format(unixTime: Long): String {
        val date = Date(unixTime * 1000)
        return formatter.format(date)
    }
}

val LocalDateFormatter = staticCompositionLocalOf { DateFormatter() }