package br.com.jwar.habittracker.shared.di

import br.com.jwar.habittracker.shared.data.datasource.HabitsLocalDatasource
import br.com.jwar.habittracker.shared.data.datasource.HabitsSQLDelightDatasource
import br.com.jwar.habittracker.shared.data.repository.HabitsDefaultRepository
import br.com.jwar.habittracker.shared.domain.repository.HabitsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    includes(platformModule())
    singleOf(::HabitsSQLDelightDatasource).bind(HabitsLocalDatasource::class)
    singleOf(::HabitsDefaultRepository).bind(HabitsRepository::class)
}