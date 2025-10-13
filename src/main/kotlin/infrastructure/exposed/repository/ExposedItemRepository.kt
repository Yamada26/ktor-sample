package com.example.infrastructure.exposed.repository

import com.example.domain.model.Item
import com.example.domain.model.User
import com.example.domain.repository.IItemRepository
import com.example.domain.repository.IUserRepository
import com.example.infrastructure.exposed.table.ItemsTable
import com.example.infrastructure.exposed.table.UsersTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedItemRepository : IItemRepository {
    override fun findAll(): List<Item> {
        // TODO: トランザクションは usecase で
        val items = transaction {
            ItemsTable.selectAll().map {
                println("item: $it")
                Item(it[ItemsTable.id].value, it[ItemsTable.name])
            }
        }
        return items
    }
}