package com.example.infrastructure.exposed.entity

import com.example.infrastructure.exposed.table.ItemsTable
import com.example.infrastructure.exposed.table.UsersTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ItemEntity(
    id: EntityID<Int>,
) : IntEntity(id) {
    companion object : IntEntityClass<ItemEntity>(ItemsTable)

    var name by ItemsTable.name
}
