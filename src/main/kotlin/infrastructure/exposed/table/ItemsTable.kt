package com.example.infrastructure.exposed.table

import org.jetbrains.exposed.dao.id.IntIdTable

object ItemsTable : IntIdTable("items") {
    val name = varchar("name", 32)
}