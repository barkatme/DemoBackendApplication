package com.barkatme.demo.setup

import com.barkatme.demo.setup.module.authModule
import com.barkatme.demo.setup.module.errorHandlerModule
import com.barkatme.demo.setup.module.serializationModule
import com.barkatme.demo.setup.module.supportModule
import com.barkatme.demo.setup.routing.homeRouting
import com.barkatme.demo.setup.routing.testRouting
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

    allRoutings()
}

fun Application.allRoutings() {
    routing {
        homeRouting()
        testRouting()
    }
}