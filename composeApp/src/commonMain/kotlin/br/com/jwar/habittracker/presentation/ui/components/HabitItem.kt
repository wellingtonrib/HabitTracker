package br.com.jwar.habittracker.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.jwar.habittracker.domain.model.HabitHistory
import br.com.jwar.habittracker.domain.model.HabitPeriod
import br.com.jwar.habittracker.domain.model.HabitStatus
import kotlinx.datetime.LocalDate

@Composable
fun HabitItem(
    name: String,
    history: HabitHistory,
    period: HabitPeriod,
    onStatusChange: (LocalDate, HabitStatus) -> Unit
) {
    val days = remember(period) { period.getDays() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = name)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            items(days) { day ->
                val dayShortName = remember(day, period) { period.getDayDisplayName(day) }
                val status = remember(history) { history[day] ?: HabitStatus.PENDING }

                HabitItemHistory(
                    name = name,
                    status = status,
                    dayShortName = dayShortName,
                    dayFullName = day.toString(),
                ) { newStatus ->
                    onStatusChange(day, newStatus)
                }
            }
        }
    }
}