package br.com.jwar.habittracker

import br.com.jwar.habittracker.di.viewModelModule
import br.com.jwar.habittracker.shared.di.dataModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            dataModule,
            viewModelModule,
        )
    }