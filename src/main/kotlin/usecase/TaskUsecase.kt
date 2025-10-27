package com.example.usecase

import com.example.domain.model.Task
import com.example.domain.model.TaskId
import com.example.domain.repository.ITaskRepository
import com.example.shared.logging.logger
import com.example.usecase.shared.ITransactionManager

data class GetTaskDTO(
        val id: Int,
        val name: String,
        val status: String,
)

data class CreateTaskDTO(
        val id: Int,
        val name: String,
        val status: String,
)

data class GetTaskCommand(
        val id: Int,
)

data class CreateTaskCommand(
        val name: String,
)

class TaskUsecase(
        private val taskRepository: ITaskRepository,
        private val txManager: ITransactionManager,
) {
        private val logger = logger<TaskUsecase>()

        fun getTask(command: GetTaskCommand): GetTaskDTO {
                val task =
                        txManager.runInTransaction {
                                return@runInTransaction taskRepository.findById(TaskId(command.id))
                        }

                logger.debug { "tasks: $task" }

                return GetTaskDTO(task?.id!!.value, task.name, task.status.name)
        }

        fun createTask(command: CreateTaskCommand): CreateTaskDTO {
                val newTask =
                        txManager.runInTransaction {
                                val task = taskRepository.save(Task(null, command.name))
                                logger.info { "Created task: $task" }
                                return@runInTransaction task
                        }

                return CreateTaskDTO(
                        id = newTask.id!!.value,
                        name = newTask.name,
                        status = newTask.status.name
                )
        }
}
