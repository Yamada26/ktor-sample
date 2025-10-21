package com.example.usecase.shared

interface ITransactionManager {
    fun <T> runInTransaction(block: () -> T): T
}
