package com.barkatme.demo.infrastructure

import com.barkatme.demo.data.dataModule
import com.barkatme.demo.domain.domainModule
import com.barkatme.demo.infrastructure.module.authModule
import com.barkatme.demo.infrastructure.module.errorHandlerModule
import com.barkatme.demo.infrastructure.module.serializationModule
import com.barkatme.demo.infrastructure.module.supportModule
import com.barkatme.demo.infrastructure.routing.homeRouting
import com.barkatme.demo.infrastructure.routing.testRouting
import com.barkatme.demo.infrastructure.module.webSocketsModule
import com.barkatme.demo.infrastructure.routing.websocket.chatWebSocket
import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.util.*
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.logger.PrintLogger
import org.koin.ktor.ext.Koin

@KtorExperimentalAPI
@Suppress("unused")
@ExperimentalSerializationApi
fun Application.configurator() {
    install(Koin) {
        logger(PrintLogger())
        modules(dataModule, infrastructureModule, domainModule)
    }
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

        chatWebSocket()
    }
}