package com.barkatme.demo.domain.repository

import com.barkatme.demo.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json


interface UserRelationRepository {

    @Serializable
    data class ViewerResult(
        @SerialName("user") val user: User,
        @SerialName("lastViewDate") val lastViewDate: String
    )

    suspend fun viewUser(userId: Int, viewedUserId: Int): Int?
    suspend fun getViewers(userId: Int): List<ViewerResult>
    suspend fun deleteViewer(viewerId: Int, userId: Int): Int
    suspend fun deleteViewers(userId: Int): Int
}

fun UserRelationRepository.ViewerResult.asString(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToString(UserRelationRepository.ViewerResult.serializer(), this)

fun UserRelationRepository.ViewerResult.asJson(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToJsonElement(UserRelationRepository.ViewerResult.serializer(), this)

fun List<UserRelationRepository.ViewerResult>.asJson(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToJsonElement(ListSerializer(UserRelationRepository.ViewerResult.serializer()), this)