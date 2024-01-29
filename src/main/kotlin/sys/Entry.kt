package sys

class Entry private constructor(
    val map: MutableMap<String,EntryField>
    ) {
    companion object {
        /**
         * Create a file from a decoded data (see ../Model)
         */
        fun existing(map: MutableMap<String, EntryField>) = Entry(map)

        /**
         * Create a new file with an empty title field. Set 'named' to false, to create no standard fields
         */
        fun empty(named: Boolean = true) = Entry(if(named) EntryTemplate.Named.fields() else EntryTemplate.None.fields())

        /**
         * Create an empty file with empty ("") fields from a specified template (see ./FileTemplate.kt).
         */
        fun fromTemplate(template: EntryTemplate) = Entry(template.fields()) //clone, copy, anything
    }
}