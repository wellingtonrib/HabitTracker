package br.com.jwar.habittracker.domain.model

enum class HabitStatus(val value: String) {
    DONE("✅"),
    SKIPPED("❌"),
    PENDING("⏳"),
}