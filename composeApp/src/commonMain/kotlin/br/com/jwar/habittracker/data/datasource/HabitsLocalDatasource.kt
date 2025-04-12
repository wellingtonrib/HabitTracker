package br.com.jwar.habittracker.data.datasource

import br.com.jwar.habittracker.database.HabitWithHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface HabitsLocalDatasource {
    fun getHabits(): Flow<List<HabitWithHistory>>
    fun addHabit(habitId: String, name: String)
    fun changeHabitStatus(habitId: String, day: LocalDate, status: String)
}