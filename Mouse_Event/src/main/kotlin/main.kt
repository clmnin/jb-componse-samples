import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.unit.IntSize

fun main() = Window(title = "Compose for Desktop", size = IntSize(400, 400)) {
    var color = remember { mutableStateOf(Color(0, 0, 0)) }
    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .fillMaxSize()
            .background(color = color.value)
            .pointerMoveFilter(
                onMove = {
                    color.value = Color(it.x.toInt() % 256, it.y.toInt() % 256, 0)
                    false
                }
            )
    )
}