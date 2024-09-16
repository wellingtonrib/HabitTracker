package br.com.jwar.habittracker.data.repository

import br.com.jwar.habittracker.data.datasource.HabitsMemoryDatasource
import br.com.jwar.habittracker.domain.model.Habit
import br.com.jwar.habittracker.domain.model.HabitStatus
import br.com.jwar.habittracker.domain.repository.HabitsRepository
import kotlinx.datetime.LocalDate

class HabitsRepositoryImpl(
    private val habitsMemoryDatasource: HabitsMemoryDatasource
): HabitsRepository {
    override fun getHabits() = habitsMemoryDatasource.getHabits()

    override fun addHabit(habit: String) = habitsMemoryDatasource.addHabit(Habit(habit))

    override fun changeHabitStatus(habit: Habit, day: LocalDate, status: HabitStatus) =
        habitsMemoryDatasource.changeHabitStatus(habit, day, status)
}