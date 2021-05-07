import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun main() = Window(title = "Compose for Desktop", size = IntSize(400, 400)) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        repeat(10) { index ->
            var active = remember { mutableStateOf(false) }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = if (active.value) Color.Green else Color.White)
                    .pointerMoveFilter(
                        onEnter = {
                            active.value = true
                            false
                        },
                        onExit = {
                            active.value = false
                            false
                        }
                    ),
                fontSize = 30.sp,
                fontStyle = if (active.value) FontStyle.Italic else FontStyle.Normal,
                text = "Item $index"
            )
        }
    }
}