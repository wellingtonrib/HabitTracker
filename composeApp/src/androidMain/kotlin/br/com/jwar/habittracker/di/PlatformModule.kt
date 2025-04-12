package br.com.jwar.habittracker.di

import br.com.jwar.habittracker.data.database.AndroidDatabaseDriverFactory
import br.com.jwar.habittracker.data.database.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun platformModule() = module {
    single<DatabaseDriverFactory> {
        AndroidDatabaseDriverFactory(androidContext())
    }
}