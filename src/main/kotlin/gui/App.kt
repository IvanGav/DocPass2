package gui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import sys.Category
import sys.Entry
import ui.OrderInfo
import ui.SysInterface

@Composable
@Preview
fun App() {
    var selectedCatId by remember { mutableStateOf("root") }
        val cats = remember { mutableStateMapOf("root" to Category.empty("docs")) }
        val entries = remember { mutableStateMapOf<String, Entry> () }
    val ui = remember { SysInterface(cats,entries, OrderInfo.empty()) }
    val expanded = remember { mutableStateMapOf<String, Unit>() }

    Body(ui, selectedCatId, expanded,
        addCat = {
            ui.createCategory("${ui.categories.size}", selectedCatId)
        }, selectCat = {
            selectedCatId = it
        }, toggleExpandCat = {
            println("--single detected")
            if(expanded.contains(it)) expanded.remove(it)
            else expanded[it] = Unit
        }
    )
}

@Composable
fun Body(
    ui: SysInterface,
    selectedCatId: String,
    expandedSet: Map<String, Unit>,
    addCat: () -> Unit,
    selectCat: (String) -> Unit,
    toggleExpandCat: (String) -> Unit
) {
    MaterialTheme {
        Row {
            Button(onClick = addCat) {
                Text("+")
            }
            Column {
                List(ui.categories, selectedCatId, expandedSet, onCatClick = toggleExpandCat, onCatDoubleClick = selectCat)
                Text("done")
            }
        }
    }
}

@Composable
fun List(
    cats: Map<String, Category>,
    selectedId: String,
    expandedSet: Map<String, Unit>,
    onCatClick: (String) -> Unit,
    onCatDoubleClick: (String) -> Unit
) {
    Box(modifier = Modifier.height(400.dp)) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            CatTreeDisplay("root",selectedId,expandedSet,0,cats,onCatClick,onCatDoubleClick)
        }
    }
}

@Composable
fun CatTreeDisplay(
    id: String,
    selectedId: String,
    expandedSet: Map<String, Unit>,
    nesting: Int,
    cats: Map<String, Category>,
    onCatClick: (String) -> Unit,
    onCatDoubleClick: (String) -> Unit
) {
    //assumes that a category with id 'id' exists
    CatDisplay(cats[id]!!.title, selectedId == id, cats[id]!!.categories.size > 0, expandedSet.contains(id), nesting, {onCatClick(id)}, {onCatDoubleClick(id)})
    if(expandedSet.contains(id))
        for(cat in cats[id]!!.categories)
            CatTreeDisplay(cat, selectedId, expandedSet, nesting+1, cats, onCatClick, onCatDoubleClick)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatDisplay(
    title: String,
    highlighted: Boolean,
    expandable: Boolean,
    expanded: Boolean,
    nesting: Int,
    onClick: () -> Unit,
    onDoubleClick: () -> Unit
) {
    val mod = Modifier.width(100.dp).combinedClickable(onDoubleClick = onDoubleClick, onClick = onClick)
    val char = if(!expandable) ' ' else if(expanded) 'v' else '>'
    Box(modifier = if(highlighted) mod.background(Color.Green) else mod
    ) {
        Text("${nest(nesting)}$char$title", modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp), fontFamily = FontFamily.Monospace)
    }
}

fun nest(n: Int): String {
    var str = ""
    for(i in 0 until n) {
        str += " "
    }
    return str
}