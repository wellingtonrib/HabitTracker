import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import br.com.jwar.habittracker.data.datasource.HabitsMemoryDatasourceImpl
import br.com.jwar.habittracker.data.repository.HabitsRepositoryImpl
import br.com.jwar.habittracker.presentation.HabitsViewModel
import br.com.jwar.habittracker.presentation.ui.screens.HabitsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val habitsMemoryDatasource = remember { HabitsMemoryDatasourceImpl() }
    val habitsRepository = remember { HabitsRepositoryImpl(habitsMemoryDatasource) }
    val viewModel = remember { HabitsViewModel(habitsRepository) }

    MaterialTheme {
        HabitsScreen(viewModel, viewModel::handleIntent)
    }
}

