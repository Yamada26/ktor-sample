package com.example

import com.example.infrastructure.exposed.ExposedDatabaseFactory
import com.example.shared.error.AppException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callid.CallId
import io.ktor.server.plugins.callid.callIdMdc
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.plugins.statuspages.exception
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.slf4j.event.Level

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(CallId) {
        header("X-Request-ID")
        generate { java.util.UUID.randomUUID().toString() }
        verify { it.isNotEmpty() }
    }

    install(CallLogging) {
        level = Level.INFO
        callIdMdc("callId")
    }

    // エラーハンドラ（StatusPages）を追加
    install(StatusPages) {
        exception<AppException> { call, cause ->
            when (cause) {
                is AppException.Invalid -> {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to cause.message))
                }
                is AppException.Unauthorized -> {
                    call.respond(HttpStatusCode.Unauthorized, mapOf("error" to cause.message))
                }
                is AppException.Forbidden -> {
                    call.respond(HttpStatusCode.Forbidden, mapOf("error" to cause.message))
                }
                is AppException.NotFound -> {
                    call.respond(HttpStatusCode.NotFound, mapOf("error" to cause.message))
                }
                is AppException.Conflict -> {
                    call.respond(HttpStatusCode.Conflict, mapOf("error" to cause.message))
                }
                is AppException.Internal -> {
                    logger.error(cause) { "Internal error" }
                    call.respond(
                            HttpStatusCode.InternalServerError,
                            mapOf("error" to cause.message)
                    )
                }
            }
        }

        exception<Throwable> { call, cause ->
            // 予期しない例外はログに出して 500 を返す
            logger.error(cause) { "Unhandled exception" }
            call.respond(
                    HttpStatusCode.InternalServerError,
                    mapOf("error" to "Internal server error")
            )
        }
    }

    ExposedDatabaseFactory.init()

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        }
    }

    routing {
        get("/") { call.respond(mapOf("message" to "hello")) }
        get("/error/internal") { throw AppException.Internal("This is a test internal error") }
        get("/error/invalid") { throw AppException.Invalid("This is a test invalid error") }
        get("/error/unauthorized") {
            throw AppException.Unauthorized("This is a test unauthorized error")
        }

        itemsRoute()
        usersRoute()
    }
}
