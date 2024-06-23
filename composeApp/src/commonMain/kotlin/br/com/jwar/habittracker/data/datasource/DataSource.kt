package br.com.jwar.habittracker.data.datasource

import br.com.jwar.habittracker.data.model.Habit
import br.com.jwar.habittracker.data.model.HabitStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object DataSource {
    private var _habits = MutableStateFlow(
        listOf(
            Habit("Drink water", "Drink 8 glasses of water every day"),
            Habit("Eat healthy", "Eat fruits and vegetables every day"),
            Habit("Exercise", "30 minutes of jogging every day")
        )
    )

    val habits: Flow<List<Habit>>
        get() = _habits.asStateFlow()

    fun addHabit(habit: Habit) {
        _habits.update { it + habit }
    }

    fun addHabitHistory(habit: String, day: String, status: HabitStatus) {
        _habits.update { habits ->
            habits.map {
                if (it.name == habit) {
                    it.copy(history = it.history + (day to status))
                } else {
                    it
                }
            }
        }
    }
}

