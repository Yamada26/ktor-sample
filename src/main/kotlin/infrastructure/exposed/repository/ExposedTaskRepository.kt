package com.example.infrastructure.exposed.repository

import com.example.domain.model.Task
import com.example.domain.model.TaskId
import com.example.domain.repository.ITaskRepository
import com.example.infrastructure.exposed.table.TasksTable
import com.example.shared.logging.logger
import org.jetbrains.exposed.sql.*

class ExposedTaskRepository : ITaskRepository {
    private val logger = logger<ITaskRepository>()

    override fun findById(id: TaskId): Task? {
        // なぜか select { TasksTable.id eq id.value } が動かない
        val taskRecord = TasksTable.selectAll().firstOrNull()

        val task =
                taskRecord?.let {
                    Task(
                            TaskId(it[TasksTable.id].value),
                            it[TasksTable.name],
                            it[TasksTable.status]
                    )
                }

        logger.debug { "Task found by id=$id: $task" }

        return task
    }

    override fun save(task: Task): Task {
        val savedTaskRecord =
                TasksTable.insert {
                    it[name] = task.name
                    it[status] = task.status
                }

        logger.debug { "Task saved: $task" }

        return Task(
                TaskId(savedTaskRecord[TasksTable.id].value),
                savedTaskRecord[TasksTable.name],
                savedTaskRecord[TasksTable.status]
        )
    }
}
