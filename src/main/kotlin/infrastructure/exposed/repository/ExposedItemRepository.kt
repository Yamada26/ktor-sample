package com.example.infrastructure.exposed.repository

import com.example.domain.model.User
import com.example.domain.repository.IUserRepository
import com.example.infrastructure.exposed.table.UsersTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedUserRepository : IUserRepository {
    override fun findAll(): List<User> {
        // TODO: トランザクションは usecase で
        val users = transaction {
            UsersTable.selectAll().map {
                println("user: $it")
                User(it[UsersTable.id].value, it[UsersTable.name])
            }
        }
        return users
    }
}