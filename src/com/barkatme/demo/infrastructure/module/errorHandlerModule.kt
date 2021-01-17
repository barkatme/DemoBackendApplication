package com.barkatme.demo.infrastructure.module

import com.barkatme.demo.infrastructure.model.exception.InvalidCredentialsException
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun Application.errorHandlerModule() {

    install(StatusPages) {
        exception<InvalidCredentialsException> { exception ->
            call.respond(HttpStatusCode.Unauthorized, exception.asString())
        }
    }
}

private fun Throwable.asString(): String = "{\"error\":\"$message\"}"