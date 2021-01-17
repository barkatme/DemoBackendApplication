package com.barkatme.demo.infrastructure.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class User(
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String
)

fun User.asString(): String = json.encodeToString(serializer, this)

fun String.asUser() = json.decodeFromString(serializer, this)

private val json = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
}

private val serializer = User.serializer()