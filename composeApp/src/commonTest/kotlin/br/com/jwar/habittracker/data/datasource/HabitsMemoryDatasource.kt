package br.com.jwar.habittracker.data.datasource

import br.com.jwar.habittracker.database.HabitWithHistory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

class HabitsMemoryDatasource : HabitsLocalDatasource {
    private var _habits = MutableStateFlow(emptyList<HabitWithHistory>())

    override fun getHabits() = _habits

    override fun addHabit(habitId: String, name: String) {
        _habits.update { currentHabits ->
            currentHabits + HabitWithHistory(
                id = habitId,
                name = name,
                date = null,
                status = null
            )
        }
    }

    override fun changeHabitStatus(habitId: String, day: LocalDate, status: String) {
        _habits.update { currentHabits ->
            currentHabits.map { habit ->
                if (habit.id == habitId) {
                    val newHistory = HabitWithHistory(
                        id = habit.id,
                        name = habit.name,
                        date = day.toString(),
                        status = status
                    )
                    newHistory
                } else {
                    habit
                }
            }
        }
    }
}