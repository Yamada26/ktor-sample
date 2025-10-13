package com.example

import com.example.presentation.controller.UserController
import com.example.presentation.form.GetHelloResponse
import com.example.presentation.form.PostHelloRequest
import com.example.presentation.form.PostHelloResponse
import com.example.usecase.ItemUsecase
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
//    route("/items") {
//        get {
//            val itemRepository: IItemRepository = ItemRepository()
//            val itemUsecase = ItemUsecase(itemRepository)
//            val itemController = ItemController(itemUsecase)
//
//            val result = itemController.getItems()
//            call.respond(result)
//        }
//    }
}

fun Routing.usersRoute() {
    route("/users") {
        get {
            // リポジトリの動作確認
            val userController = UserController()
            val users = userController.getUsers()
            call.respond(users)
        }
    }
}