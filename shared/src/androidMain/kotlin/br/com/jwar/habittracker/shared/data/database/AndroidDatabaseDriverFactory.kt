package br.com.jwar.habittracker.shared.data.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import br.com.jwar.habittracker.database.AppDatabase

class AndroidDatabaseDriverFactory(private val context: Context) : DatabaseDriverFactory {
    override fun create(): SqlDriver =
        AndroidSqliteDriver(AppDatabase.Companion.Schema, context, "habittracker.db")
}