import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier

class ItemList(
    database: Database, // Accept the Database as dependency
    val onItemSelected: (itemId: Long) -> Unit // Called on item click
) {
    // No concurrency involved just for simplicity. The state can be updated if needed.
    private val _state = mutableStateOf(database.getAll())
    val state: State<List<Item>> = _state
}

@Composable
fun ItemListScreen(items: List<Item>, onItemClick: (id: Long) -> Unit) {
    LazyColumn {
        items(items = items) { item ->
            Text(
                text = item.text,
                modifier = Modifier.clickable { onItemClick(item.id) }
            )
        }
    }
}

@Composable
fun ItemListUi(list: ItemList) {
    ItemListScreen(
        items = list.state.value,
        onItemClick = list.onItemSelected
    )
}