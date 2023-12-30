//package gui
//
//import androidx.compose.desktop.ui.tooling.preview.Preview
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Button
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import sys.Category
//import ui.SysInterface
//
//@Composable
//@Preview
//fun App() {
//    var ui by remember { mutableStateOf(SysInterface.empty()) }
//    var addToCat by remember { mutableStateOf("root") }
//    var x by remember { mutableStateMapOf(*ui.categories.toList().toTypedArray()) }
//
//    Body(ui,
//        {
//            ui.createCategory("${ui.categories.size}", addToCat)
//            ui = ui.copy()
//        }, {
//            addToCat = it
//        }
//    )
//}
//
//@Composable
//fun Body(
//    ui: SysInterface,
//    addCat: () -> Unit,
//    onCatClick: (String) -> Unit
//) {
//    MaterialTheme {
//        Row {
//            Button(onClick = addCat) {
//                Text("+")
//            }
//            Column {
//                List(ui.categories, onCatClick)
//                Text("done")
//            }
//        }
//    }
//}
//
//@Composable
//fun List(cats: Map<String, Category>, onCatClick: (String) -> Unit) {
//    Box(modifier = Modifier.height(200.dp)) {
//        Column(
//            modifier = Modifier.verticalScroll(rememberScrollState())
//        ) {
//            CatTreeDisplay("root",0,cats, onCatClick)
//        }
//    }
//}
//
//@Composable
//fun CatTreeDisplay(id: String, nesting: Int, cats: Map<String, Category>, onCatClick: (String) -> Unit) {
//    //assumes that a category exists
//    CatDisplay(id, nesting, cats) { onCatClick(id) }
//    for(cat in cats[id]!!.categories) {
//        CatTreeDisplay(cat, nesting+1, cats, onCatClick)
//    }
//}
//
//@Composable
//fun CatDisplay(id: String, nesting: Int, cats: Map<String, Category>, onClick: (String) -> Unit) {
//    Box(modifier = Modifier.
//    border(2.dp, Color.Black, RoundedCornerShape(20)).
//    width(100.dp).
//    clickable { onClick(id) }
//    ) {
//        Text("${nest(nesting)}${cats[id]!!.title}", modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp))
//    }
//}
//
//fun nest(n: Int): String {
//    var str = ""
//    for(i in 0 until n) {
//        str += "="
//    }
//    return str
//}