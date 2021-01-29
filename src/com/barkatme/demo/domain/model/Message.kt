package com.barkatme.demo.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Serializable
data class Message(
    @SerialName("id") val id: Int = 0,
    @SerialName("nickName") val nickName: String = "",
    @SerialName("time") val time: Long = System.currentTimeMillis(),
    @SerialName("text") val text: String = "",
)

fun Message.asString(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToString(Message.serializer(), this)

fun Message.asJson(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToJsonElement(Message.serializer(), this)

fun List<Message>.asJson(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToJsonElement(ListSerializer(Message.serializer()), this)

fun String.asMessage(): Message = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
}.decodeFromString(Message.serializer(), this)