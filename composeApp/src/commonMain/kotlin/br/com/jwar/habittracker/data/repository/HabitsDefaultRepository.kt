package br.com.jwar.habittracker.data.repository

import br.com.jwar.habittracker.data.datasource.HabitsLocalDatasource
import br.com.jwar.habittracker.database.HabitWithHistory
import br.com.jwar.habittracker.domain.model.Habit
import br.com.jwar.habittracker.domain.model.HabitStatus
import br.com.jwar.habittracker.domain.repository.HabitsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

expect fun randomUUID(): String

class HabitsDefaultRepository(
    private val habitsLocalDatasource: HabitsLocalDatasource
): HabitsRepository {
    override fun getHabits(): Flow<List<Habit>> =
        habitsLocalDatasource.getHabits().map { it.toHabits() }

    override fun addHabit(habitName: String) = try {
        val habit = Habit(id = randomUUID(), name = habitName)
        habitsLocalDatasource.addHabit(habit.id, habit.name)
        Result.success(habit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun changeHabitStatus(habitId: String, day: LocalDate, status: HabitStatus) = try {
        habitsLocalDatasource.changeHabitStatus(habitId = habitId, day = day, status = status.name)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun List<HabitWithHistory>.toHabits(): List<Habit> {
        return this.groupBy { it.id }.map { (_, habitWithHistory) ->
            val habit = habitWithHistory.first()
            val history = habitWithHistory
                .filter { it.date != null && it.status != null }
                .associate { LocalDate.parse(it.date!!) to HabitStatus.valueOf(it.status!!) }
            Habit(
                id = habit.id,
                name = habit.name,
                history = history,
            )
        }
    }
}