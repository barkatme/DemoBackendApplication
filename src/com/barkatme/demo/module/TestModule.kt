package com.barkatme.demo.module

import com.barkatme.demo.domain.SimpleJWT
import com.barkatme.demo.model.User
import com.barkatme.demo.model.exception.InvalidCredentialsException
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.util.*

@ExperimentalSerializationApi
@Suppress("unused")
fun Application.testModule(@Suppress("UNUSED_PARAMETER") testing: Boolean = false) {

    val simpleJwt = SimpleJWT("my-super-secret-for-jwt")

    val users = Collections.synchronizedMap(
        listOf(User("test@mail.com", "testUserName"))
            .associateBy { it.email }
            .toMutableMap()
    )

    install(ContentNegotiation) {
        serialization(
            contentType = ContentType.Application.Json,
            object : StringFormat {
                override val serializersModule: SerializersModule
                    get() = SerializersModule {
                        json(Json {
                            ignoreUnknownKeys = true
                            encodeDefaults = true
                        })
                    }

                override fun <T> decodeFromString(deserializer: DeserializationStrategy<T>, string: String): T =
                    Json {
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                    }.decodeFromString(deserializer, string)

                override fun <T> encodeToString(serializer: SerializationStrategy<T>, value: T): String =
                    Json {
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                    }.encodeToString(serializer, value)
            }
        )
    }

    install(Authentication) {
        jwt {
            verifier(simpleJwt.verifier)
            validate {
                UserIdPrincipal(it.payload.getClaim("email").asString())
            }
            authSchemes()
        }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        allowCredentials = true
        anyHost()
    }

    install(StatusPages) {
//        exception<InvalidCredentialsException> { exception ->
//            call.respond(HttpStatusCode.Unauthorized, exception.asString())
//        }
        exception<Throwable> { throwable ->
            call.respond(HttpStatusCode.InternalServerError, throwable.asString())
        }
    }

    routing {
        route("/test") {

            post {
                val post = call.receive<User>()
                val user = users.getOrPut(post.email) { User(post.email, post.name) }
                if (user.name != post.name) throw InvalidCredentialsException("name doesn't match")
                call.respond(mapOf("token" to simpleJwt.sign(user.name)))
            }

            authenticate {
                get {
                    call.respondText {
                        "secret text"
                    }
                }
            }
        }
    }
}

private fun Throwable.asString(): String = "{\"error\":\"$message\"}"
