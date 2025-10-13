package com.example

import com.example.domain.repository.IItemRepository
import com.example.domain.repository.IUserRepository
import com.example.infrastructure.exposed.repository.ExposedItemRepository
import com.example.infrastructure.exposed.repository.ExposedUserRepository
import com.example.presentation.controller.ItemController
import com.example.presentation.controller.UserController
import com.example.presentation.form.GetHelloResponse
import com.example.presentation.form.PostHelloRequest
import com.example.presentation.form.PostHelloResponse
import com.example.usecase.ItemUsecase
import com.example.usecase.UserUsecase
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.helloRoute() {
    route("/hello") {
        get {
//            val helloController = HelloController()
//            val response = helloController.getHello()
            call.respond(GetHelloResponse("Hello World!"))
        }

        post {
            val request = call.receive<PostHelloRequest>()
            val response = PostHelloResponse("Hello, ${request.name}!")
            call.respond(response.result)
        }
    }
}

fun Routing.itemsRoute() {
    route("/items") {
        get {
            val itemRepository: IItemRepository = ExposedItemRepository()
            val itemUsecase = ItemUsecase(itemRepository)
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
            val userUsecase = UserUsecase(userRepository)
            val userController = UserController(userUsecase)
            
            val result = userController.getUsers()
            call.respond(result)
        }
    }
}