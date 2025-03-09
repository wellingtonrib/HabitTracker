package br.com.jwar.habittracker.data.datasource

import br.com.jwar.habittracker.domain.model.Habit
import br.com.jwar.habittracker.domain.model.HabitStatus
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

interface HabitsLocalDatasource {
    fun getHabits(): StateFlow<List<Habit>>
    fun addHabit(habit: Habit)
    fun changeHabitStatus(habitId: String, day: LocalDate, status: HabitStatus)
}