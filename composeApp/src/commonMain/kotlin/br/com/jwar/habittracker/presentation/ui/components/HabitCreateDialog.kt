package br.com.jwar.habittracker.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.jwar.habittracker.data.model.Habit

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HabitCreateDialog(
    onHabitAdded: (Habit) -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState()
    val newHabitName = remember { mutableStateOf("") }
    val newHabitDescription = remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Create a new habit",
                style = MaterialTheme.typography.subtitle1,
            )
            OutlinedTextField(
                value = newHabitName.value,
                onValueChange = { newHabitName.value = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Habit name") },
            )
            OutlinedTextField(
                value = newHabitDescription.value,
                onValueChange = { newHabitDescription.value = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Habit description") }
            )
            Button(
                onClick = {
                    onHabitAdded(Habit(newHabitName.value, newHabitDescription.value))
                    onDismissRequest()
                },
                enabled = newHabitName.value.isNotBlank()
            ) {
                Text("Add Habit")
            }
        }
    }
}