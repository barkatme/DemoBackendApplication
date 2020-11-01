package com.barkatme.demo.setup.module

import com.barkatme.demo.model.exception.InvalidCredentialsException
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