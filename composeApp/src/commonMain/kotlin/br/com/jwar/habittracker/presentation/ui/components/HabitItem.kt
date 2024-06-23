package br.com.jwar.habittracker.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.jwar.habittracker.data.model.Habit
import br.com.jwar.habittracker.data.model.HabitPeriod
import br.com.jwar.habittracker.data.model.HabitStatus
import kotlinx.datetime.LocalDate

@Composable
fun HabitItem(
    habit: Habit,
    days: List<LocalDate>,
    period: HabitPeriod,
    onHistoryChanged: (LocalDate, HabitStatus) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = habit.name)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            items(days) { day ->
                HabitItemHistory(habit, day, period) { status ->
                    onHistoryChanged(day, status)
                }
            }
        }
    }
}