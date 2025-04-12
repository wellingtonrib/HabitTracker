package br.com.jwar.habittracker.presentation.ui

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

    fun handleIntent(intent: HabitsIntent) {
        when (intent) {
            is HabitsIntent.Init -> onInit()
            is HabitsIntent.AddHabit -> onAddHabit(intent)
            is HabitsIntent.ChangeHabitStatus -> onChangeHabitStatus(intent)
            is HabitsIntent.ChangePeriod -> onChangePeriod(intent)
        }
    }

    private fun onInit() = viewModelScope.launch {
        habitsRepository.getHabits().collect { result ->
            _state.update { it.copy(habits = result) }
        }
    }

    private fun onChangePeriod(intent: HabitsIntent.ChangePeriod) {
        _state.update { it.copy(selectedPeriod = intent.period) }
    }

    private fun onAddHabit(intent: HabitsIntent.AddHabit) = viewModelScope.launch {
        val result = habitsRepository.addHabit(intent.name)
        if (result.isFailure) {
            // handle error
        }
    }

    private fun onChangeHabitStatus(
        intent: HabitsIntent.ChangeHabitStatus,
    ) = viewModelScope.launch {
        val result = habitsRepository.changeHabitStatus(
            habitId = intent.habitId,
            day = intent.day,
            status = intent.status
        )
        if (result.isFailure) {
            // handle error
        }
    }
}