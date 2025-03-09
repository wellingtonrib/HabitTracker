import androidx.compose.ui.window.ComposeUIViewController
import br.com.jwar.habittracker.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    MainApp()
}