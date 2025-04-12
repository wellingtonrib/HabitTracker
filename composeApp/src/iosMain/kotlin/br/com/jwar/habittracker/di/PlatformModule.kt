package br.com.jwar.habittracker.di

import br.com.jwar.habittracker.data.database.DatabaseDriverFactory
import br.com.jwar.habittracker.data.database.IOSDatabaseDriverFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single<DatabaseDriverFactory> {
        IOSDatabaseDriverFactory()
    }
}