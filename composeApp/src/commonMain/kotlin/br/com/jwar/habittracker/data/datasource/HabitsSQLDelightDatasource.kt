package br.com.jwar.habittracker.data.datasource

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import br.com.jwar.habittracker.data.database.DatabaseDriverFactory
import br.com.jwar.habittracker.database.AppDatabase
import br.com.jwar.habittracker.database.HabitWithHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

class HabitsSQLDelightDatasource(
    databaseDriverFactory: DatabaseDriverFactory
) : HabitsLocalDatasource {
    private val driver = databaseDriverFactory.create()
    private val database = AppDatabase(driver)

    override fun getHabits(): Flow<List<HabitWithHistory>> =
        database.habitQueries.habitWithHistory()
            .asFlow()
            .mapToList(Dispatchers.IO)

    override fun addHabit(habitId: String, name: String) {
        database.habitQueries.insert(
            id = habitId,
            name = name,
        )
    }

    override fun changeHabitStatus(habitId: String, day: LocalDate, status: String) {
        database.habitQueries.insertHistory(
            habitId = habitId,
            date = day.toString(),
            status = status,
        )
    }
}