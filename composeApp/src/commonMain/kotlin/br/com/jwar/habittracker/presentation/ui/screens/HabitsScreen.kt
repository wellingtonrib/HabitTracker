package br.com.jwar.habittracker.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.jwar.habittracker.domain.model.HabitPeriod
import br.com.jwar.habittracker.presentation.HabitsIntent
import br.com.jwar.habittracker.presentation.HabitsViewModel
import br.com.jwar.habittracker.presentation.ui.components.HabitCreateDialog
import br.com.jwar.habittracker.presentation.ui.components.HabitItem
import br.com.jwar.habittracker.presentation.ui.components.HabitPeriodMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitsScreen(
    viewModel: HabitsViewModel,
    onIntent: (HabitsIntent) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    var showNewHabitDialog by remember { mutableStateOf(false) }
    if (showNewHabitDialog) {
        HabitCreateDialog(
            onHabitAdded = { name -> onIntent(HabitsIntent.AddHabit(name)) },
            onDismissRequest = { showNewHabitDialog = false }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Habit Tracker")
                },
                actions = {
                    ChangePeriodAction(state.selectedPeriod) { newPeriod ->
                        onIntent(HabitsIntent.ChangePeriod(newPeriod))
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showNewHabitDialog = true }
            ) {
                Icon(Icons.Filled.Add, "Add habit")
            }
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = contentPadding,
            content = {
                items(state.habits) { habit ->
                    HabitItem(
                        name = habit.name,
                        history = habit.history,
                        period = state.selectedPeriod,
                    ) { day, status ->
                        onIntent(HabitsIntent.ChangeHabitStatus(habit, day, status))
                    }
                }
            }
        )
    }
}

@Composable
private fun ChangePeriodAction(
    period: HabitPeriod,
    onPeriodChange: (HabitPeriod) -> Unit
) {
    var showPeriodMenu by remember { mutableStateOf(false) }

    HabitPeriodMenu(
        expanded = showPeriodMenu,
        periods = HabitPeriod.entries.map { it.name },
        selectedPeriod = period,
        onPeriodSelected = { newPeriod ->
            onPeriodChange(newPeriod)
            showPeriodMenu = false
        },
        onDismissRequest = { showPeriodMenu = false }
    )

    Text(period.displayName)
    IconButton(onClick = { showPeriodMenu = true }) {
        Icon(Icons.Filled.DateRange, contentDescription = "Select period")
    }
}