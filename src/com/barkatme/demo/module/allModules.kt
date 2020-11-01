package com.barkatme.demo.module

import com.barkatme.demo.module.routing.testRouting
import io.ktor.application.*
import io.ktor.routing.*
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@Suppress("unused")
fun Application.allModules(@Suppress("UNUSED_PARAMETER") testing: Boolean = false) {

    supportModule()
    authModule()
    errorHandlerModule()
    serializationModule()

    routing {
        testRouting()
    }
}
