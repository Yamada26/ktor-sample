package com.example.domain.repository

import com.example.domain.model.Task
import com.example.domain.model.TaskId

interface ITaskRepository {
    fun findById(id: TaskId): Task?
    fun save(task: Task): Task
}
