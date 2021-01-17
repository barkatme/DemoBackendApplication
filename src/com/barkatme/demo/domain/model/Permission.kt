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

fun Permission.asString(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToString(Permission.serializer(), this)

fun Permission.asJson(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToJsonElement(Permission.serializer(), this)

fun List<Permission>.asJson(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToJsonElement(ListSerializer(Permission.serializer()), this)
