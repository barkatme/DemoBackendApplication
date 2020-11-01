package com.barkatme.demo

import com.barkatme.demo.setup.configurator
import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import kotlinx.serialization.ExperimentalSerializationApi

class RootTests {
    @ExperimentalSerializationApi
    @Test
    fun testIfRootTextHtml() {
        withTestApplication({ configurator(testing = true) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Text.Html.contentType, response.contentType().contentType)
                assertEquals(ContentType.Text.Html.contentSubtype, response.contentType().contentSubtype)
            }
        }
    }
}
