package com.barkatme.demo.module.routing

import io.ktor.application.*
import io.ktor.routing.*

fun Application.allRoutings(){
    routing {
        homeRouting()
        testRouting()
    }
}