package br.com.jwar.habittracker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jwar.habittracker.domain.repository.HabitsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HabitsViewModel(
    private val habitsRepository: HabitsRepository,
): ViewModel() {
    private val _state = MutableStateFlow(HabitsState())
    val state: StateFlow<HabitsState> = _state

    init {
        observeHabits()
    }

    private fun observeHabits() = viewModelScope.launch {
        habitsRepository.getHabits().collect { habits ->
            _state.update { it.copy(habits = habits) }
        }
    }

    fun handleIntent(intent: HabitsIntent) {
        when (intent) {
            is HabitsIntent.AddHabit -> onHabitAdd(intent)
            is HabitsIntent.ChangeHabitStatus -> onHabitStatusChange(intent)
            is HabitsIntent.ChangePeriod -> onPeriodChange(intent)
        }
    }

    private fun onPeriodChange(intent: HabitsIntent.ChangePeriod) {
        _state.update { it.copy(selectedPeriod = intent.period) }
    }

    private fun onHabitAdd(intent: HabitsIntent.AddHabit) = viewModelScope.launch {
        habitsRepository.addHabit(intent.name)
    }

    private fun onHabitStatusChange(
        intent: HabitsIntent.ChangeHabitStatus,
    ) = viewModelScope.launch {
        habitsRepository.changeHabitStatus(
            habit = intent.habit,
            day = intent.day,
            status = intent.status
        )
    }
}