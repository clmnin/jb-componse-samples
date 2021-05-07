import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorXmlResource

fun main() {
    Window {
        Image(
            imageVector = vectorXmlResource("images/compose-logo.xml"),
            contentDescription = "Compose logo",
            modifier = Modifier.fillMaxSize()
        )
    }
}