package br.com.jwar.habittracker.domain.model

data class Habit(
    val id: String,
    val name: String,
    val history: HabitHistory = emptyMap()
)

