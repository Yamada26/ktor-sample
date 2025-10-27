package com.example.infrastructure.exposed.table

import com.example.domain.model.TaskStatus
import org.jetbrains.exposed.dao.id.IntIdTable

object TasksTable : IntIdTable("tasks") {
    val name = varchar("name", 32)
    val status = enumerationByName("status", 32, TaskStatus::class).default(TaskStatus.TODO)
}
