package br.com.jwar.habittracker

import br.com.jwar.habittracker.data.datasource.HabitsLocalDatasource
import br.com.jwar.habittracker.data.datasource.HabitsMemoryDatasource
import br.com.jwar.habittracker.data.repository.HabitsRepositoryImpl
import br.com.jwar.habittracker.domain.repository.HabitsRepository
import br.com.jwar.habittracker.presentation.ui.HabitsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            provideDatasourceModule,
            provideRepositoryModule,
            provideViewModelModule,
        )
    }

val provideRepositoryModule = module {
    singleOf(::HabitsRepositoryImpl).bind(HabitsRepository::class)
}

val provideDatasourceModule = module {
    singleOf(::HabitsMemoryDatasource).bind(HabitsLocalDatasource::class)
}

val provideViewModelModule = module {
    viewModelOf(::HabitsViewModel)
}