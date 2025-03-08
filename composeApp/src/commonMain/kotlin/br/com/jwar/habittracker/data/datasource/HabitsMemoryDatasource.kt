package br.com.jwar.habittracker.data.datasource

import br.com.jwar.habittracker.domain.model.Habit
import br.com.jwar.habittracker.domain.model.HabitStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

class HabitsMemoryDatasource : HabitsLocalDatasource {
    private var _habits = MutableStateFlow(emptyList<Habit>())

    override fun getHabits() = _habits.asStateFlow()

    override fun addHabit(habit: Habit) {
        _habits.update { currentHabits ->
            currentHabits + habit
        }
    }

    override fun changeHabitStatus(habitId: String, day: LocalDate, status: HabitStatus) {
        _habits.update { currentHabits ->
            currentHabits.map { currentHabit ->
                if (currentHabit.id == habitId) {
                    currentHabit.copy(history = currentHabit.history + (day to status))
                } else {
                    currentHabit
                }
            }
        }
    }
}