package br.com.jwar.habittracker.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import br.com.jwar.habittracker.database.AppDatabase

class IOSDatabaseDriverFactory: DatabaseDriverFactory {
    override fun create(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "habittracker.db")
    }
}