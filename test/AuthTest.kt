package com.barkatme.demo

import com.barkatme.demo.model.User
import com.barkatme.demo.model.asString
import com.barkatme.demo.setup.configurator
import com.barkatme.demo.setup.module.asToken
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.Test
import kotlin.test.assertEquals

class AuthTest {
    @ExperimentalSerializationApi
    @Test
    fun jwtTokenTest() {
        withTestApplication({ configurator(testing = true) }) {
            val user = User("test@mail.com", "testUserName")
            val token = handleRequest(HttpMethod.Post, "/auth") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(user.asString())
            }.response.content!!.asToken()
            handleRequest(HttpMethod.Get, "/test") {
                addHeader(HttpHeaders.Authorization, "Bearer " + token.token)
            }.apply {
                assertEquals("secret text", response.content)
            }
        }
    }
}