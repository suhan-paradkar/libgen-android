
import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wisestwizard.scigen.model.Book
import com.wisestwizard.scigen.utils.downloadBook
import com.wisestwizard.scigen.views.BookItemView

@Composable
fun Booklist(itemList: MutableList<Book> = ArrayList(), context: Context) {
    if (itemList.size == 0) {
        return
    }
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(count = itemList.size, itemContent = { index ->
            BookItemView(
                title = itemList[index].title?:"",
                author = itemList[index].author?:"",
                ext = itemList[index].extension?:"",
                size = itemList[index].size?:"",
                pages = itemList[index].pages?:"",
                year = itemList[index].year?:"",
                language = itemList[index].language?:"",
                onDownloadClick = { downloadBook(index, itemList, context) },
                onShareClick = { }
            )
        })
    }
}