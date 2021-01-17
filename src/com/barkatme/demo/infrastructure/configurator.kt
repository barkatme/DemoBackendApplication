package com.barkatme.demo.infrastructure

import com.barkatme.demo.infrastructure.module.authModule
import com.barkatme.demo.infrastructure.module.errorHandlerModule
import com.barkatme.demo.infrastructure.module.serializationModule
import com.barkatme.demo.infrastructure.module.supportModule
import com.barkatme.demo.infrastructure.routing.homeRouting
import com.barkatme.demo.infrastructure.routing.testRouting
import com.barkatme.demo.infrastructure.module.webSocketsModule
import com.barkatme.demo.infrastructure.routing.websocket.testWebSocketRoute
import io.ktor.application.*
import io.ktor.routing.*
import kotlinx.serialization.ExperimentalSerializationApi

@Suppress("unused")
@ExperimentalSerializationApi
fun Application.configurator() {
    supportModule()
    authModule()
    errorHandlerModule()
    serializationModule()
    webSocketsModule()

    allRoutings()
}

fun Application.allRoutings() {
    routing {
        homeRouting()
        testRouting()
        testWebSocketRoute()
    }
}