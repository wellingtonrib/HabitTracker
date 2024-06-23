package br.com.jwar.habittracker.data.model

enum class HabitStatus(val value: String) {
    DONE("✅"),
    SKIPPED("❌"),
    PENDING("⏳"),
}