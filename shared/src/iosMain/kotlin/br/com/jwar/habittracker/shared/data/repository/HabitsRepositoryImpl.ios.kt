package br.com.jwar.habittracker.shared.data.repository

import platform.Foundation.NSUUID

actual fun randomUUID() = NSUUID().UUIDString