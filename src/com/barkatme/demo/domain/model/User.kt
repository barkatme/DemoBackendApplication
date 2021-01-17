package com.barkatme.demo.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json


@Serializable
data class User(
    @SerialName("id") val id: Int = 0,
    @SerialName("login") val login: String? = null,
    @Transient val isDeleted: Boolean = false,
    @SerialName("firstName") val firstName: String? = null,
    @SerialName("lastName") val lastName: String? = null,
    @SerialName("about") val about: String? = null,
    @SerialName("email") val email: String,
    @SerialName("online") val isOnline: Boolean? = null,
    @SerialName("role") val role: Int = 0,
    @SerialName("dateRegistration") val dateRegistration: String? = null,

    @Transient val passwordHash: String = "",
    @Transient var token: String? = null
)

fun User.asString(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToString(User.serializer(), this)

fun User.asJson(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToJsonElement(User.serializer(), this)

fun List<User>.asJson(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToJsonElement(ListSerializer(User.serializer()), this)