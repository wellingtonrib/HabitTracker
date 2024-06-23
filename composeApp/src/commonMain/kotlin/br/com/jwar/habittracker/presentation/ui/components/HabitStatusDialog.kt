package br.com.jwar.habittracker.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.jwar.habittracker.data.model.Habit
import br.com.jwar.habittracker.data.model.HabitStatus
import kotlinx.datetime.LocalDate

@Composable
fun HabitStatusDialog(
    habit: Habit,
    date: LocalDate,
    onStatusChanged: (HabitStatus) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = habit.name,
                    style = MaterialTheme.typography.h6
                )
                Text(text = date.toString())
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(HabitStatus.entries.toTypedArray()) { status ->
                        Button(
                            onClick = { onStatusChanged(status) }
                        ) {
                            Text(status.value)
                        }
                    }
                }
            }
        }
    }
}