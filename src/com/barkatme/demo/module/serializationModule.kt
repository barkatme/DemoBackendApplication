package com.barkatme.demo.module

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.serialization.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
fun Application.serializationModule(){
    install(ContentNegotiation) {
        serialization(
            contentType = ContentType.Application.Json,
            object : StringFormat {
                override val serializersModule: SerializersModule
                    get() = SerializersModule {
                        json(Json {
                            ignoreUnknownKeys = true
                            encodeDefaults = true
                        })
                    }

                override fun <T> decodeFromString(deserializer: DeserializationStrategy<T>, string: String): T =
                    Json {
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                    }.decodeFromString(deserializer, string)

                override fun <T> encodeToString(serializer: SerializationStrategy<T>, value: T): String =
                    Json {
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                    }.encodeToString(serializer, value)
            }
        )
    }
}