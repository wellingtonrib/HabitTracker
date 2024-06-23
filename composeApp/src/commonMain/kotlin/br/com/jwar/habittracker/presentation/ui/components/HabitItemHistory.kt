package br.com.jwar.habittracker.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.jwar.habittracker.data.model.Habit
import br.com.jwar.habittracker.data.model.HabitPeriod
import br.com.jwar.habittracker.data.model.HabitStatus
import kotlinx.datetime.LocalDate

@Composable
fun HabitItemHistory(
    habit: Habit,
    date: LocalDate,
    period: HabitPeriod,
    onStatusChange: (HabitStatus) -> Unit
) {
    val day = remember(date, period) { period.getDayDisplayName(date) }
    val status = habit.history[date.toString()] ?: HabitStatus.PENDING

    var showHabitStatus by remember { mutableStateOf(false) }

    if (showHabitStatus) {
        HabitStatusDialog(
            habit = habit,
            date = date,
            onStatusChanged = { updatedStatus ->
                onStatusChange(updatedStatus)
                showHabitStatus = false
            },
            onDismissRequest = { showHabitStatus = false }
        )
    }

    Card(
        modifier = Modifier.clickable { showHabitStatus = true },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = day,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
            )
            Text(text = status.value)
        }
    }
}