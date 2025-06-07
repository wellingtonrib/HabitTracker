package br.com.jwar.habittracker.shared.domain.model

enum class HabitStatus(val value: String) {
    DONE("✅"),
    SKIPPED("❌"),
    PENDING("⏳"),
}