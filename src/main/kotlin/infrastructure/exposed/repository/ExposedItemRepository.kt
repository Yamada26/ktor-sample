package com.example.infrastructure.exposed.repository

import com.example.domain.model.Item
import com.example.domain.model.ItemId
import com.example.domain.repository.IItemRepository
import com.example.infrastructure.exposed.table.ItemsTable
import com.example.shared.logging.logger
import org.jetbrains.exposed.sql.selectAll

class ExposedItemRepository : IItemRepository {
    private val logger = logger<IItemRepository>()

    override fun findAll(): List<Item> {
        val items =
                ItemsTable.selectAll().map {
                    println("item: $it")
                    Item(ItemId(it[ItemsTable.id].value), it[ItemsTable.name])
                }

        logger.debug { "Items found: $items" }

        return items
    }
}
