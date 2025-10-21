package com.example

import com.example.domain.repository.IItemRepository
import com.example.domain.repository.IUserRepository
import com.example.infrastructure.exposed.repository.ExposedItemRepository
import com.example.infrastructure.exposed.repository.ExposedUserRepository
import com.example.infrastructure.exposed.shared.ExposedTransactionManager
import com.example.presentation.controller.ItemController
import com.example.presentation.controller.UserController
import com.example.usecase.ItemUsecase
import com.example.usecase.UserUsecase
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
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
