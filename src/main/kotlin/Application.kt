package com.example

import org.slf4j.event.Level
import com.example.infrastructure.exposed.ExposedDatabaseFactory
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import io.ktor.serialization.jackson.jackson
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.server.plugins.calllogging.*

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(CallLogging) {
        level = Level.INFO
    }

    ExposedDatabaseFactory.init()

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        }
    }

    routing {
        get("/") {
            call.respond(mapOf("message" to "hello"))
        }

        itemsRoute()
        usersRoute()
    }
}
