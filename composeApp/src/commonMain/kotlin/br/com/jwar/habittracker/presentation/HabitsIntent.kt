package br.com.jwar.habittracker.presentation

import br.com.jwar.habittracker.domain.model.Habit
import br.com.jwar.habittracker.domain.model.HabitPeriod
import br.com.jwar.habittracker.domain.model.HabitStatus
import kotlinx.datetime.LocalDate

sealed class HabitsIntent {
    data class AddHabit(val name: String) : HabitsIntent()
    data class ChangeHabitStatus(val habit: Habit, val day: LocalDate, val status: HabitStatus) : HabitsIntent()
    data class ChangePeriod(val period: HabitPeriod) : HabitsIntent()
}