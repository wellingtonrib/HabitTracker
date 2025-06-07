package br.com.jwar.habittracker.shared.data.repository

import java.util.UUID

actual fun randomUUID() = UUID.randomUUID().toString()