package com.example.infrastructure.exposed

import com.example.infrastructure.exposed.entity.UserEntity
import com.example.infrastructure.exposed.table.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select

object ExposedDatabaseFactory {
    fun init() {
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/mydatabase",
            driver = "org.postgresql.Driver",
            user = "myuser",
            password = "mypassword"
        )

        transaction {
            val user = UserEntity.new {
                name = "test"
            }
            println("Inserted id: ${user.id}")

            UserEntity.findById(user.id)?.let {
                println("id: ${it.id}")
                println("name: ${it.id}")
            }
        }
    }
}