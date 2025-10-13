package com.example.infrastructure.database.repository

import com.example.domain.model.Item
import com.example.domain.repository.IItemRepository

class ItemRepository : IItemRepository {
    override fun findAll(): List<Item> {
        return listOf(Item(1, "item1"), Item(2, "item2"), Item(3, "item3"))
    }
}