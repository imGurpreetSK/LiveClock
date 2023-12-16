package com.gurpreetsk.liveclock.utils

import java.time.LocalTime
import java.time.temporal.Temporal

interface Clock<out T : Temporal> {
    fun now(): T
}

internal class LocalDateTimeClock : Clock<LocalTime> {
    override fun now(): LocalTime = LocalTime.now()
}
