package br.com.jwar.habittracker.shared.di

import br.com.jwar.habittracker.shared.data.database.DatabaseDriverFactory
import br.com.jwar.habittracker.shared.data.database.IOSDatabaseDriverFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single<DatabaseDriverFactory> {
        IOSDatabaseDriverFactory()
    }
}