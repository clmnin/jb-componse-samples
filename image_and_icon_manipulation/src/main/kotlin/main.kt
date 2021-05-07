import androidx.compose.desktop.AppManager
import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import org.jetbrains.skija.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO

fun main() {
    val image = getWindowIcon()
    Window {
        val imageAsset = remember { asImageAsset(image) }
        Image(
            bitmap = imageAsset,
            contentDescription = "Icon",
            modifier = Modifier.fillMaxSize()
        )
    }

    AppManager.focusedWindow?.setIcon(image)
}

fun getWindowIcon(): BufferedImage {
    var image: BufferedImage? = null
    try {
        image = ImageIO.read(File("/home/clament/Desktop/sample.png"))
    } catch (e: Exception) {
        // image file does not exist
    }

    if (image == null) {
        image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }

    return image
}

fun asImageAsset(image: BufferedImage): ImageBitmap {
    val baos = ByteArrayOutputStream()
    ImageIO.write(image, "png", baos)

    return Image.makeFromEncoded(baos.toByteArray()).asImageBitmap()
}