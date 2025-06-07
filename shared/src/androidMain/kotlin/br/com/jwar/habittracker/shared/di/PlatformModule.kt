package br.com.jwar.habittracker.shared.di

import br.com.jwar.habittracker.shared.data.database.AndroidDatabaseDriverFactory
import br.com.jwar.habittracker.shared.data.database.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun platformModule() = module {
    single<DatabaseDriverFactory> {
        AndroidDatabaseDriverFactory(androidContext())
    }
}