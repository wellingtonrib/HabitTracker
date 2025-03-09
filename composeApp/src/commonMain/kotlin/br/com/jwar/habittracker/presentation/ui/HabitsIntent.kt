package br.com.jwar.habittracker.presentation.ui

import br.com.jwar.habittracker.domain.model.HabitPeriod
import br.com.jwar.habittracker.domain.model.HabitStatus
import kotlinx.datetime.LocalDate

sealed class HabitsIntent {
    data object Init : HabitsIntent()
    data class AddHabit(val name: String) : HabitsIntent()
    data class ChangeHabitStatus(
        val habitId: String,
        val day: LocalDate,
        val status: HabitStatus,
    ) : HabitsIntent()
    data class ChangePeriod(val period: HabitPeriod) : HabitsIntent()
}