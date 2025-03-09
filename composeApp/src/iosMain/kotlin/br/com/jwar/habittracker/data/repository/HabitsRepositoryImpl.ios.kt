package br.com.jwar.habittracker.data.repository

import platform.Foundation.NSUUID

actual fun randomUUID() = NSUUID().UUIDString