package com.example.presentation.controller

import com.example.presentation.form.CreateTaskRequest
import com.example.presentation.form.CreateTaskResponse
import com.example.presentation.form.GetTaskRequest
import com.example.presentation.form.GetTaskResponse
import com.example.shared.logging.logger
import com.example.usecase.CreateTaskCommand
import com.example.usecase.GetTaskCommand
import com.example.usecase.TaskUsecase

class TaskController(
        private val taskUsecase: TaskUsecase,
) {
    private val logger = logger<TaskController>()

    fun getTask(request: GetTaskRequest): GetTaskResponse {
        val command = GetTaskCommand(id = request.id)
        val task = taskUsecase.getTask(command)

        logger.debug { "tasks: $task" }

        return GetTaskResponse(
                task.id,
                task.name,
                task.status,
        )
    }

    fun createTask(request: CreateTaskRequest): CreateTaskResponse {
        val command = CreateTaskCommand(name = request.name)
        val newTask = taskUsecase.createTask(command)

        logger.info { "Created task: $newTask" }

        return CreateTaskResponse(
                id = newTask.id,
                name = newTask.name,
                status = newTask.status,
        )
    }
}
