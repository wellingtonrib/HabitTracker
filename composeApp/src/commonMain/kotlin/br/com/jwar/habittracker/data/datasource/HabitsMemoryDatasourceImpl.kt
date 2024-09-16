package br.com.jwar.habittracker.data.datasource

import br.com.jwar.habittracker.domain.model.Habit
import br.com.jwar.habittracker.domain.model.HabitStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

class HabitsMemoryDatasourceImpl : HabitsMemoryDatasource {
    private var _habits = MutableStateFlow(
        listOf(
            Habit("Drink water"),
            Habit("Eat healthy"),
            Habit("Exercise")
        )
    )

    override fun getHabits() = _habits.asStateFlow()

    override fun addHabit(habit: Habit) {
        _habits.update { currentHabits ->
            currentHabits + habit
        }
    }

    override fun changeHabitStatus(habit: Habit, day: LocalDate, status: HabitStatus) {
        _habits.update { currentHabits ->
            currentHabits.map { currentHabit ->
                if (currentHabit.name == habit.name) {
                    currentHabit.copy(history = currentHabit.history + (day to status))
                } else {
                    currentHabit
                }
            }
        }
    }
}