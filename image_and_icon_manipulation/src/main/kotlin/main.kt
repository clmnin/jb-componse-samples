import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import org.jetbrains.skija.Image
import java.io.File

fun main() {
    Window {
        val image = remember { imageFromFile(File("/home/clament/Desktop/sample.png")) }
        Image(
            bitmap = image,
            contentDescription = "Sample",
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun imageFromFile(file: File): ImageBitmap {
    return Image.makeFromEncoded(file.readBytes()).asImageBitmap()
}