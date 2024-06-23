package br.com.jwar.habittracker.data.model

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

enum class HabitPeriod {
    WEEK,
    MONTH,
    YEAR;

    fun getDays() = when (this) {
        WEEK -> getWeekDays()
        MONTH -> getMonthDays()
        YEAR -> getYearDays()
    }

    fun getDayDisplayName(date: LocalDate) = when (this) {
        WEEK -> date.dayOfWeek.name.substring(0, 3)
        MONTH -> "${date.dayOfWeek.name.substring(0, 3)}\n${date.dayOfMonth}"
        YEAR -> "${date.dayOfMonth}\n${date.month.name.substring(0, 3)}"
    }
}

fun getWeekDays(): List<LocalDate> {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val weekStart = today.minus(today.dayOfWeek.ordinal.toLong(), DateTimeUnit.DAY)
    return (0..6).map { weekStart.plus(it.toLong(), DateTimeUnit.DAY) }
}

fun getMonthDays(): List<LocalDate> {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val monthStart = today.minus(today.dayOfMonth - 1, DateTimeUnit.DAY)
    val daysInMonth = monthStart.daysUntil(monthStart.plus(1, DateTimeUnit.MONTH))
    return (0 until daysInMonth).map { monthStart.plus(it.toLong(), DateTimeUnit.DAY) }
}

fun getYearDays(): List<LocalDate> {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val yearStart = today.minus(today.dayOfYear - 1, DateTimeUnit.DAY)
    val daysInYear = yearStart.daysUntil(yearStart.plus(1, DateTimeUnit.YEAR))
    return (0 until daysInYear).map { yearStart.plus(it.toLong(), DateTimeUnit.DAY) }
}