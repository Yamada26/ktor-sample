package com.example.infrastructure.exposed.table

import org.jetbrains.exposed.dao.id.IntIdTable

object UsersTable : IntIdTable("users") {
    val name = varchar("name", 32)
}
