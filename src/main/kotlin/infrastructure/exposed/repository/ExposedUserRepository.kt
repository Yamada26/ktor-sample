package com.example.infrastructure.exposed.repository

import com.example.domain.model.User
import com.example.domain.model.UserId
import com.example.domain.repository.IUserRepository
import com.example.infrastructure.exposed.table.UsersTable
import com.example.shared.logging.logger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedUserRepository : IUserRepository {
    private val logger = logger<IUserRepository>()

    override fun findAll(): List<User> {
        // TODO: トランザクションは usecase で
        val users =
            transaction {
                UsersTable.selectAll().map {
                    println("user: $it")
                    User(UserId(it[UsersTable.id].value), it[UsersTable.name])
                }
            }

        logger.debug { "Items found: $users" }

        return users
    }
}
