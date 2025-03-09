package br.com.jwar.habittracker.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HabitCreateDialog(
    onHabitAdded: (String) -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState()
    val newHabitName = remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
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
            Button(
                onClick = {
                    onHabitAdded(newHabitName.value)
                    onDismissRequest()
                },
                enabled = newHabitName.value.isNotBlank()
            ) {
                Text("Add Habit")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}