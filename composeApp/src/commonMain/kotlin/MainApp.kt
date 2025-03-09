import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import br.com.jwar.habittracker.presentation.ui.HabitsViewModel
import br.com.jwar.habittracker.presentation.ui.HabitsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun MainApp() {
    val viewModel = koinViewModel<HabitsViewModel>()
    val state by viewModel.state.collectAsState()

    MaterialTheme {
        HabitsScreen(state, viewModel::handleIntent)
    }
}

