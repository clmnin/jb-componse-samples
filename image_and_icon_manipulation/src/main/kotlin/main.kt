import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource

fun main() {
    Window {
        Image(
            bitmap = imageResource("images/sample.png"), // ImageBitmap
            contentDescription = "Sample",
            modifier = Modifier.fillMaxSize()
        )
    }
}