package ui

class OrderInfo private constructor(val order: MutableMap<String,MutableList<String>>) {
    companion object {
        fun empty() = OrderInfo(mutableMapOf())
        fun existing(order: MutableMap<String,MutableList<String>>) = OrderInfo(order)
    }
}