import androidx.compose.desktop.Window
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.AwaitPointerEventScope
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntSize
import java.awt.event.MouseEvent

fun main() = Window(title = "Compose for Desktop", size = IntSize(400, 400)) {
    var lastEvent by remember { mutableStateOf<MouseEvent?>(null) }
    Column {
        Text(
            text = "Custom button",
            modifier = Modifier.pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        lastEvent = awaitEventFirstDown().also {
                            it.changes.forEach { it.consumeDownChange() }
                        }.mouseEvent
                    }
                }
            }
        )
        Text("Mouse event: ${lastEvent?.paramString()}")
    }

}

private suspend fun AwaitPointerEventScope.awaitEventFirstDown(): PointerEvent {
    var event: PointerEvent
    do {
        event = awaitPointerEvent()
    } while (
        !event.changes.all { it.changedToDown() }
    )
    return event
}