package com.barkatme.demo.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Serializable
data class Role(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String,
    @SerialName("createdAt") val createdAt: String? = null,
)

fun Role.asString(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToString(Role.serializer(), this)

fun Role.asJson(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToJsonElement(Role.serializer(), this)

fun List<Role>.asJson(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToJsonElement(ListSerializer(Role.serializer()), this)