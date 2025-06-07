package br.com.jwar.habittracker.presentation.ui

import br.com.jwar.habittracker.shared.domain.model.Habit
import br.com.jwar.habittracker.shared.domain.model.HabitPeriod

data class HabitsState(
    val habits: List<Habit> = emptyList(),
    val selectedPeriod: HabitPeriod = HabitPeriod.WEEK,
)