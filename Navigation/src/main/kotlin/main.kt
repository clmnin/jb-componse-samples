import androidx.compose.desktop.DesktopTheme
import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.rememberRootComponent

fun main() {
    Window("Navigation tutorial") {
        Surface(modifier = Modifier.fillMaxSize()) {
            MaterialTheme {
                DesktopTheme {
                    RootUi(root()) // Render the Root and its children
                }
            }
        }
    }
}

@Composable
private fun root(): Root =
    // The rememberRootComponent function provides the root ComponentContext and remembers the instance or Root
    rememberRootComponent { componentContext ->
        Root(
            componentContext = componentContext,
            database = DatabaseImpl() // Supply dependencies
        )
    }