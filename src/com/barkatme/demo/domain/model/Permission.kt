package com.barkatme.demo.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Serializable
data class Permission(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String,
    @SerialName("createdAt") val createdAt: String? = null,
)

fun Credentials.asString(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToString(Credentials.serializer(), this)

fun Credentials.asJson(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToJsonElement(Credentials.serializer(), this)

fun List<Credentials>.asJson(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToJsonElement(ListSerializer(Credentials.serializer()), this)
