import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.shortcuts

fun main() = Window(title = "Compose for Desktop", size = IntSize(300, 300)) {
    MaterialTheme {
        // What is remember -
        // it is used as a state. When the state changes it UI is recomposed
        // remember is a function that gives another composable function memory.
        // A value computed by `remember` is stored in the composition during initial composition, and that stored
        // value is returned during recomposition. You can use remember to store both mutable and immutable objects.
        var consumedText by remember { mutableStateOf(0) }
        var text by remember { mutableStateOf("") }
        Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
            Text("Consumed text: $consumedText")
            TextField(
                value = text,
                onValueChange = { text = it },
                // Add behavior to Compose UI elements.
                // For example, backgrounds, padding and click event listeners decorate or add behavior to rows, text or buttons.
                // Here
                // We use the KeyEvent modifier shortcuts and add events using on
                modifier = Modifier.shortcuts {
                    on(Key.CtrlLeft + Key.Minus) {
                        consumedText -= text.length
                        text = ""
                    }
                    on(Key.CtrlLeft + Key.Equals) {
                        consumedText += text.length
                        text = ""
                    }
                }
            )
        }
    }
}