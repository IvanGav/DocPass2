package sys

//"Title" field should be everywhere where it's appropriate
enum class EntryTemplate {
    None, Named;

    fun fields(): MutableMap<String, EntryField> {
        return when(this) {
            None -> mutableMapOf()
            Named -> mutableMapOf("Title" to StrField(""))
        }
    }
}