package br.com.jwar.habittracker

import MainApp
import androidx.compose.ui.window.ComposeUIViewController
import br.com.jwar.habittracker.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    MainApp()
}