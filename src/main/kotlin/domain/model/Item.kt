package com.example.domain.model

data class Item(
    val id: Int,
    val name: String
) {
    init {
        require(name.isNotEmpty()) { "Item name must not be empty" }
    }
}
