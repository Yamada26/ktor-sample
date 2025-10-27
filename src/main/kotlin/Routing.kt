package com.example

import com.example.domain.repository.IItemRepository
import com.example.domain.repository.ITaskRepository
import com.example.domain.repository.IUserRepository
import com.example.infrastructure.exposed.repository.ExposedItemRepository
import com.example.infrastructure.exposed.repository.ExposedTaskRepository
import com.example.infrastructure.exposed.repository.ExposedUserRepository
import com.example.infrastructure.exposed.shared.ExposedTransactionManager
import com.example.presentation.controller.ItemController
import com.example.presentation.controller.TaskController
import com.example.presentation.controller.UserController
import com.example.presentation.form.CreateItemRequest
import com.example.presentation.form.CreateTaskRequest
import com.example.presentation.form.GetTaskRequest
import com.example.shared.error.AppException
import com.example.usecase.ItemUsecase
import com.example.usecase.TaskUsecase
import com.example.usecase.UserUsecase
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Routing.itemsRoute() {
    route("/items") {
        get {
            val itemRepository: IItemRepository = ExposedItemRepository()
            val txManager = ExposedTransactionManager()
            val itemUsecase = ItemUsecase(itemRepository, txManager)
            val itemController = ItemController(itemUsecase)

            val result = itemController.getItems()
            call.respond(result)
        }

        post {
            val itemRepository: IItemRepository = ExposedItemRepository()
            val txManager = ExposedTransactionManager()
            val itemUsecase = ItemUsecase(itemRepository, txManager)
            val itemController = ItemController(itemUsecase)

            val request = call.receive<CreateItemRequest>()
            val result = itemController.createItem(request)
            call.respond(result)
        }
    }
}

fun Routing.tasksRoute() {
    route("/tasks") {
        get("/{id}") {
            val taskRepository: ITaskRepository = ExposedTaskRepository()
            val txManager = ExposedTransactionManager()
            val taskUsecase = TaskUsecase(taskRepository, txManager)
            val taskController = TaskController(taskUsecase)

            val id =
                    (call.parameters["id"] ?: "").toIntOrNull()
                            ?: throw AppException.Invalid("Invalid task id")
            val result = taskController.getTask(GetTaskRequest(id))
            call.respond(result)
        }

        post {
            val taskRepository: ITaskRepository = ExposedTaskRepository()
            val txManager = ExposedTransactionManager()
            val taskUsecase = TaskUsecase(taskRepository, txManager)
            val taskController = TaskController(taskUsecase)

            val request = call.receive<CreateTaskRequest>()
            val result = taskController.createTask(request)
            call.respond(result)
        }
    }
}

fun Routing.usersRoute() {
    route("/users") {
        get {
            val userRepository: IUserRepository = ExposedUserRepository()
            val txManager = ExposedTransactionManager()
            val userUsecase = UserUsecase(userRepository, txManager)
            val userController = UserController(userUsecase)

            val result = userController.getUsers()
            call.respond(result)
        }
    }
}
