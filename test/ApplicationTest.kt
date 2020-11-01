package com.barkatme.demo

import com.barkatme.demo.setup.allModules
import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import kotlinx.serialization.ExperimentalSerializationApi

class ApplicationTest {
    @ExperimentalSerializationApi
    @Test
    fun testRoot() {
        withTestApplication({ allModules(testing = true) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD!", response.content)
            }
        }
    }
}
