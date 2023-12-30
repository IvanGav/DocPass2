package sys

class Category private constructor(
    var title: String,
    val categories: MutableList<String>,
    val entries: MutableList<String>
    ) {
    companion object {
        fun existing(title: String, categories: MutableList<String>, entries: MutableList<String>) = Category(title, categories, entries)
        fun empty(title: String) = Category(title, mutableListOf(), mutableListOf())
    }
}