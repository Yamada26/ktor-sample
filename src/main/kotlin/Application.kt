package com.example

import io.ktor.server.application.*
import io.ktor.server.routing.routing

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
    routing {
        greetingRoute()
    }
}
