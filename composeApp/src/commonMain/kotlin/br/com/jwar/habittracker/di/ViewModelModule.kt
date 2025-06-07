package br.com.jwar.habittracker.di

import br.com.jwar.habittracker.presentation.ui.HabitsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HabitsViewModel)
}