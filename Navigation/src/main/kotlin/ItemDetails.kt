import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.mutableStateOf

class ItemDetails(
    itemId: Long, // An item id to be loaded and displayed
    database: Database, // Accept the Database as dependency
    val onFinished: () -> Unit // Called on TopAppBar back button click
) {
    // No concurrency involved just for simplicity. The state can be updated if needed.
    private val _state = mutableStateOf(database.getById(id = itemId))
    val state: State<Item> = _state
}

@Composable
fun ItemDetailsScreen(item: Item, onBackClick: () -> Unit) {
    Column {
        TopAppBar(
            title = { Text("Item details") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )

        Text(text = item.text)
    }
}

@Composable
fun ItemDetailsUi(details: ItemDetails) {
    ItemDetailsScreen(
        item = details.state.value,
        onBackClick = details.onFinished
    )
}