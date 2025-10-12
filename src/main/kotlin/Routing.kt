package com.example

import com.example.presentation.controller.HelloController
import com.example.presentation.form.GetHelloResponse
import com.example.presentation.form.PostHelloRequest
import com.example.presentation.form.PostHelloResponse
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/test") {
            call.respondText("This is a test")
        }
    }
}

fun Routing.greetingRoute() {
    route("/greeting") {
        get("/hello") {
            call.respondText("Hello Ktor!")
        }

        get("/goodmorning") {
            call.respondText("Good Morning!")
        }
    }
}

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

