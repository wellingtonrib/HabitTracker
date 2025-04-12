package br.com.jwar.habittracker.domain.repository

import br.com.jwar.habittracker.domain.model.Habit
import br.com.jwar.habittracker.domain.model.HabitStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface HabitsRepository {
    fun getHabits(): Flow<List<Habit>>
    fun addHabit(habitName: String): Result<Habit>
    fun changeHabitStatus(habitId: String, day: LocalDate, status: HabitStatus): Result<Unit>
}