package com.barkatme.demo.module

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*

fun Application.supportModule() {

    install(DefaultHeaders) {}

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        allowCredentials = true
        anyHost()
    }
}