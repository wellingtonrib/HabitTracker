package br.com.jwar.habittracker.domain.model

data class Habit(
    val name: String,
    val history: HabitHistory = emptyMap()
)

