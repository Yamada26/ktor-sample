package com.example.presentation.form

data class ItemInfo(
        val id: Int,
        val name: String,
)

data class GetItemsResponse(
        val items: List<ItemInfo>,
)

data class CreateItemRequest(
        val name: String,
)

data class CreateItemResponse(
        val id: Int,
        val name: String,
)
