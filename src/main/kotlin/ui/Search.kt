package ui

import sys.Entry
import sys.StrField

class Search(private val entries: Map<String,Entry>) {
    //names of all fields that exist
    //for now, assume that their number is relatively little
    private val allFields: MutableSet<String> = HashSet() //mutableSetOf() //consider using HashSet
    //number of occurrences of every field, if it's important
    private val fieldCount: MutableMap<String, Int> = mutableMapOf()
    init {
        for(entry in entries.values) {
            for (field in entry.map.keys) {
                allFields.add(field)
                if(fieldCount[field]?.plus(1) == null)
                    fieldCount[field] = 1
            }
        }
    }

    fun searchTitleStart(search: String, ignoreCase: Boolean = true): List<String> {
        val searchResults = mutableListOf<String>()
        for(entry in entries) {
            val title = entry.value.map["Title"]
            if(title != null && title is StrField)
                if(title.data.startsWith(search, ignoreCase))
                    searchResults.add(entry.key)
        }
        return searchResults
    }

    fun searchTitleAnywhere(search: String, ignoreCase: Boolean = true): List<String> {
        val searchResults = mutableListOf<String>()
        for(entry in entries) {
            val title = entry.value.map["Title"]
            if(title != null && title is StrField)
                if(title.data.contains(search, ignoreCase))
                    searchResults.add(entry.key)
        }
        return searchResults
    }
}