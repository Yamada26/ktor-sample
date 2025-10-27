package com.example.domain.model

import com.example.shared.error.AppException
import com.example.shared.logging.logger

enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE
}

class Task(
        val id: TaskId?,
        val name: String,
        val status: TaskStatus = TaskStatus.TODO,
) {
    private val logger = logger<Task>()

    init {
        if (name.isEmpty()) {
            logger.error { "Invalid task name: '$name'" }
            throw AppException.Invalid("Task name must not be empty")
        }
    }

    fun start(): Task {
        if (status != TaskStatus.TODO) {
            logger.error { "Cannot start task with status: $status" }
            throw AppException.Invalid("Only TODO tasks can be started")
        }
        return Task(id, name, TaskStatus.IN_PROGRESS)
    }

    fun complete(): Task {
        if (status != TaskStatus.IN_PROGRESS) {
            logger.error { "Cannot complete task with status: $status" }
            throw AppException.Invalid("Only IN_PROGRESS tasks can be completed")
        }
        return Task(id, name, TaskStatus.DONE)
    }

    fun reset(): Task {
        if (status == TaskStatus.TODO) {
            logger.error { "Cannot reset task with status: $status" }
            throw AppException.Invalid("Task is already in TODO status")
        }
        return Task(id, name, TaskStatus.TODO)
    }
}
