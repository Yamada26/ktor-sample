package com.example.infrastructure.exposed

import com.example.infrastructure.exposed.table.ItemsTable
import com.example.infrastructure.exposed.table.TasksTable
import com.example.infrastructure.exposed.table.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object ExposedDatabaseFactory {
    fun init() {
        // Use SQLite for local/simple setups. This uses a local file `app.db`.
        // Make sure you have the SQLite JDBC dependency (org.xerial:sqlite-jdbc) in your build.
        Database.connect(url = "jdbc:sqlite:app.db", driver = "org.sqlite.JDBC")

        // Create tables if they don't exist yet.
        transaction { SchemaUtils.create(UsersTable, ItemsTable, TasksTable) }
    }
}
