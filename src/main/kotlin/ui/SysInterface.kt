package ui

import sys.Category
import sys.Entry
import sys.EntryTemplate
import kotlin.random.Random

class SysInterface(
    val categories: MutableMap<String, Category>,
    val entries: MutableMap<String, Entry>,
    val order: OrderInfo,
    val search: Search = Search(entries)
    ) {
    companion object {
        fun empty() = SysInterface(mutableMapOf("root" to Category.empty("docs")), mutableMapOf(),OrderInfo.empty())
    }

    //_______________________________________________Add/delete/move__________________________________________________//

    //create a category with name 'title' inside of category with id 'insideId'
    //return an id of a newly created category
    //INCOMPLETE
    fun createCategory(title: String, insideId: String): String? {
        if(categories[insideId] == null) return null
        val newId = "${Random.nextInt()}" //use a generator here
        categories[insideId]!!.categories.add(newId)
        categories[newId] = Category.empty(title)
        return newId
    }

    //create an entry with name 'title' inside of category with id 'insideId'
    //return an id of a newly created entry
    //INCOMPLETE
    fun createEntry(title: String, insideId: String, template: EntryTemplate = EntryTemplate.Named): String? {
        if(categories[insideId] == null) return null
        val newId = "${Random.nextInt()}" //use a generator here
        categories[insideId]!!.entries.add(newId)
        entries[newId] = Entry.fromTemplate(template)
        return newId
    }

    //delete an entry with id 'id'
    //optionally provide an id of a category in which it's contained, if known
    fun deleteEntry(id: String, insideId: String? = null): Boolean {
        if(entries[id] == null) return false
        if(insideId == null || categories[insideId] == null) {
            categories[whereEntry(id)]!!.entries.remove(id)
        } else {
            if(!categories[insideId]!!.entries.remove(id))
                return false
        }
        return entries.remove(id) != null
    }

    //delete an entry with id 'id'
    //optionally provide an id of a category in which it's contained, if known
    fun deleteCategory(id: String, insideId: String? = null): Boolean {
        if(categories[id] == null) return false
        if(insideId == null || categories[insideId] == null) {
            categories[whereEntry(id)]!!.categories.remove(id)
        } else {
            if(!categories[insideId]!!.categories.remove(id))
                return false
        }
        return categories.remove(id) != null
    }

    fun moveEntry(id: String, toCategoryId: String, fromCategoryId: String? = null): Boolean {
        if(!categories.containsKey(toCategoryId)) return false
        if(fromCategoryId == null || categories[fromCategoryId] == null) {
            categories[whereEntry(id)]!!.entries.remove(id)
        } else {
            categories[fromCategoryId]!!.entries.remove(id)
        }
        categories[toCategoryId]!!.entries.add(id)
        return true
    }

    fun moveCategory(id: String, toCategoryId: String, fromCategoryId: String? = null): Boolean {
        if(!categories.containsKey(toCategoryId)) return false
        if(fromCategoryId == null || categories[fromCategoryId] == null) {
            categories[whereCategory(id)]!!.categories.remove(id)
        } else {
            categories[fromCategoryId]!!.categories.remove(id)
        }
        categories[toCategoryId]!!.categories.add(id)
        return true
    }

    //____________________________________________________Entry_______________________________________________________//

    fun getEntry(id: String) = entries[id]

    //____________________________________________________Find________________________________________________________//

    //returns an id of a category where this entry is located
    fun whereEntry(id: String): String? {
        if(!entries.containsKey(id)) return null
        for(cat in categories) {
            if(cat.value.entries.contains(id)) {
                return cat.key
            }
        }
        return null
    }

    //returns an id of a category where this category is located
    fun whereCategory(id: String): String? {
        if(!categories.containsKey(id)) return null
        for(cat in categories) {
            if(cat.value.categories.contains(id)) {
                return cat.key
            }
        }
        return null
    }

    fun copy(): SysInterface {
        return SysInterface(categories, entries, order, search)
    }
}