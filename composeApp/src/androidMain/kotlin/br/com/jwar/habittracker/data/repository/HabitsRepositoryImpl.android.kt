package br.com.jwar.habittracker.data.repository

actual fun randomUUID() = java.util.UUID.randomUUID().toString()