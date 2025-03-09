package br.com.jwar.habittracker.presentation.ui.components

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import br.com.jwar.habittracker.domain.model.HabitPeriod

@Composable
fun HabitPeriodMenu(
    expanded: Boolean,
    periods: List<String>,
    selectedPeriod: HabitPeriod,
    onPeriodSelected: (HabitPeriod) -> Unit = {},
    onDismissRequest: () -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest() }
    ) {
        periods.forEach { period ->
            val habitPeriod = HabitPeriod.valueOf(period)
            DropdownMenuItem(
                text = { Text(period) },
                leadingIcon = {
                    if (habitPeriod == selectedPeriod) {
                        Icon(Icons.Filled.Done, contentDescription = "Selected period")
                    }
                },
                onClick = {
                    onPeriodSelected(habitPeriod)
                }
            )
        }
    }
}