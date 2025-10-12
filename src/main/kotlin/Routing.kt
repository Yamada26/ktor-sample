package com.example

import io.ktor.server.application.*
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

