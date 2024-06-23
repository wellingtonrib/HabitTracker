import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import br.com.jwar.habittracker.presentation.ui.screens.HabitsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        HabitsScreen()
    }
}

