package br.com.jwar.habittracker.data.repository

import br.com.jwar.habittracker.data.datasource.HabitsLocalDatasource
import br.com.jwar.habittracker.domain.model.Habit
import br.com.jwar.habittracker.domain.model.HabitStatus
import br.com.jwar.habittracker.domain.repository.HabitsRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

expect fun randomUUID(): String

class HabitsRepositoryImpl(
    private val habitsLocalDatasource: HabitsLocalDatasource
): HabitsRepository {
    override fun getHabits() = try {
        habitsLocalDatasource.getHabits().map { Result.success(it) }
    } catch (e: Exception) {
        flowOf(Result.failure(e))
    }

    override fun addHabit(habitName: String) = try {
        val habit = Habit(id = randomUUID(), name = habitName)
        habitsLocalDatasource.addHabit(habit)
        Result.success(habit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun changeHabitStatus(habitId: String, day: LocalDate, status: HabitStatus) = try {
        habitsLocalDatasource.changeHabitStatus(habitId = habitId, day = day, status = status)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}