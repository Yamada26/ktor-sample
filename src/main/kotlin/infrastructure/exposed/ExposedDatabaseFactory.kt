package com.example.infrastructure.exposed

import com.example.infrastructure.exposed.entity.UserEntity
import com.example.infrastructure.exposed.table.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object ExposedDatabaseFactory {
    fun init() {
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/mydatabase",
            driver = "org.postgresql.Driver",
            user = "myuser",
            password = "mypassword",
        )
    }
}
