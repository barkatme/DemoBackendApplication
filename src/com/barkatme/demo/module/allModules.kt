package com.barkatme.demo.module

import com.barkatme.demo.module.routing.allRoutings
import io.ktor.application.*
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@Suppress("unused")
fun Application.allModules(@Suppress("UNUSED_PARAMETER") testing: Boolean = false) {
    supportModule()
    authModule()
    errorHandlerModule()
    serializationModule()

    //there's a fun same but only for routing extensions, so define new routing inside it
    allRoutings()
}
