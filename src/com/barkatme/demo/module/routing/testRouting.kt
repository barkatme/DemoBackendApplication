package com.barkatme.demo.module.routing

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.testRouting() {
    authenticate {
        get("/test") {
            call.respondText {
                "secret text"
            }
        }
    }
}