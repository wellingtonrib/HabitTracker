package br.com.jwar.habittracker.data.model

data class Habit(
    val name: String,
    val description: String,
    val history: HabitHistory = emptyMap()
)

