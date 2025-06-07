package br.com.jwar.habittracker.shared.data.database

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {
    fun create(): SqlDriver
}