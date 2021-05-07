import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.svgResource

fun main() {
    Window {
        Image(
            painter = svgResource("images/idea-logo.svg"),
            contentDescription = "Idea logo",
            modifier = Modifier.fillMaxSize()
        )
    }
}