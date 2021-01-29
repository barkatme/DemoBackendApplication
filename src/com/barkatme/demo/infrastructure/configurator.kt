package com.barkatme.demo.infrastructure

import com.barkatme.demo.data.DatabaseFactory
import com.barkatme.demo.data.dataModule
import com.barkatme.demo.domain.domainModule
import com.barkatme.demo.infrastructure.module.authModule
import com.barkatme.demo.infrastructure.module.errorHandlerModule
import com.barkatme.demo.infrastructure.module.serializationModule
import com.barkatme.demo.infrastructure.module.supportModule
import com.barkatme.demo.infrastructure.routing.homeRouting
import com.barkatme.demo.infrastructure.routing.testRouting
import com.barkatme.demo.infrastructure.module.webSocketsModule
import com.barkatme.demo.infrastructure.routing.userRouting
import com.barkatme.demo.infrastructure.routing.websocket.chatWebSocket
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.routing.*
import io.ktor.util.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.logger.PrintLogger
import org.koin.ktor.ext.Koin

@FlowPreview
@ExperimentalCoroutinesApi
@KtorExperimentalAPI
@Suppress("unused")
@ExperimentalSerializationApi
fun Application.configurator() {
    DatabaseFactory.init()
    install(Koin) {
        logger(PrintLogger())
        modules(dataModule, infrastructureModule, domainModule)
    }

    supportModule()
    serializationModule()
    authModule()
    errorHandlerModule()
    install(Locations) {
    }

    allRoutings()
}

fun Application.allRoutings() {

    webSocketsModule()

    routing {
        homeRouting()
        testRouting()
        userRouting()

        chatWebSocket()
    }
}