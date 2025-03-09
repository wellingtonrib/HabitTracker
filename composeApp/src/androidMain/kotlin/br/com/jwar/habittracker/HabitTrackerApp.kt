package br.com.jwar.habittracker

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class HabitTrackerApp: Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@HabitTrackerApp)
        }
    }
}