package br.com.jwar.habittracker.shared.domain.model

data class Habit(
    val id: String,
    val name: String,
    val history: HabitHistory = emptyMap()
)

