package sys

//"Title" field should be everywhere where it's appropriate
enum class EntryTemplate(val map: MutableMap<String, EntryField>) {
    None(mutableMapOf()),
    Named(mutableMapOf("Title" to StrField(""))),
    One(mutableMapOf("One" to StrField(""))),
    Two(mutableMapOf("One" to StrField(""), "Two" to StrField("")));
}