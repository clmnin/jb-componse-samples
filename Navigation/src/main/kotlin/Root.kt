import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.pop
import com.arkivanov.decompose.push
import com.arkivanov.decompose.router
import com.arkivanov.decompose.statekeeper.Parcelable

sealed class Configuration : Parcelable {
    object List : Configuration()
    data class Details(val itemId: Long) : Configuration()
}

typealias Content = @Composable () -> Unit

fun <T : Any> T.asContent(content: @Composable (T) -> Unit): Content = { content(this) }

class Root(
    componentContext: ComponentContext, // In Decompose each component has its own ComponentContext
    private val database: Database // Accept the Database as dependency
) : ComponentContext by componentContext {

    private val router =
        router<Configuration, Content>(
            initialConfiguration = Configuration.List, // Starting with List
            childFactory = ::createChild // The Router calls this function, providing the child Configuration and ComponentContext
        )

    val routerState = router.state

    private fun createChild(configuration: Configuration, context: ComponentContext): Content =
        when (configuration) {
            is Configuration.List -> list()
            is Configuration.Details -> details(configuration)
        } // Configurations are handled exhaustively

    private fun list(): Content =
        ItemList(
            database = database, // Supply dependencies
            onItemSelected = { router.push(Configuration.Details(itemId = it)) } // Push Details on item click
        ).asContent { ItemListUi(it) }

    private fun details(configuration: Configuration.Details): Content =
        ItemDetails(
            itemId = configuration.itemId, // Safely pass arguments
            database = database, // Supply dependencies
            onFinished = router::pop // Go back to List
        ).asContent { ItemDetailsUi(it) }
}

@Composable
fun RootUi(root: Root) {
    Children(root.routerState) { child ->
        child.instance()
    }
}