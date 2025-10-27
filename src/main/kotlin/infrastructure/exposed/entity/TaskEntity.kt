package com.example.infrastructure.exposed.entity

import com.example.infrastructure.exposed.table.TasksTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TaskEntity(
        id: EntityID<Int>,
) : IntEntity(id) {
    companion object : IntEntityClass<TaskEntity>(TasksTable)

    var name by TasksTable.name
    var status by TasksTable.status
}
