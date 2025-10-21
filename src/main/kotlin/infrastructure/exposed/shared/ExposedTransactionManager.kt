package com.example.infrastructure.exposed.shared

import com.example.usecase.shared.ITransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedTransactionManager : ITransactionManager {
    override fun <T> runInTransaction(block: () -> T): T = transaction { block() }
}
