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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import br.com.jwar.habittracker.data.datasource.DataSource
import br.com.jwar.habittracker.data.model.HabitPeriod
import br.com.jwar.habittracker.presentation.ui.components.HabitCreateDialog
import br.com.jwar.habittracker.presentation.ui.components.HabitItem
import br.com.jwar.habittracker.presentation.ui.components.HabitPeriodMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitsScreen() {
    var showNewHabitDialog by remember { mutableStateOf(false) }
    var showPeriodMenu by remember { mutableStateOf(false) }
    var selectedPeriod by remember { mutableStateOf(HabitPeriod.WEEK) }

    val days = remember(selectedPeriod) { selectedPeriod.getDays() }
    val habits by DataSource.habits.collectAsState(emptyList())

    if (showNewHabitDialog) {
        HabitCreateDialog(
            onHabitAdded = { habit -> DataSource.addHabit(habit) },
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
                    Text(selectedPeriod.name.toLowerCase(Locale.current).capitalize(Locale.current))
                    IconButton(onClick = { showPeriodMenu = true }) {
                        Icon(Icons.Filled.DateRange, contentDescription = "Select period")
                    }
                    HabitPeriodMenu(
                        expanded = showPeriodMenu,
                        periods = HabitPeriod.entries.map { it.name },
                        selectedPeriod = selectedPeriod,
                        onPeriodSelected = { period ->
                            selectedPeriod = period
                            showPeriodMenu = false
                        },
                        onDismissRequest = { showPeriodMenu = false }
                    )
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
                items(habits) { habit ->
                    HabitItem(
                        habit = habit,
                        days = days,
                        period = selectedPeriod,
                    ) { day, status ->
                        DataSource.addHabitHistory(habit.name, day.toString(), status)
                    }
                }
            }
        )
    }
}