package com.example

import com.example.infrastructure.exposed.ExposedDatabaseFactory
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callid.CallId
import io.ktor.server.plugins.callid.callIdMdc
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.slf4j.event.Level

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain
        .main(args)
}

fun Application.module() {
    install(CallId) {
        header("X-Request-ID")
        generate {
            java.util.UUID
                .randomUUID()
                .toString()
        }
        verify { it.isNotEmpty() }
    }

    install(CallLogging) {
        level = Level.INFO
        callIdMdc("callId") // ← これを追加！
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
