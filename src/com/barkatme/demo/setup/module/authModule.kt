package com.barkatme.demo.setup.module

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.barkatme.demo.model.User
import com.barkatme.demo.model.exception.InvalidCredentialsException
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.*

private open class SimpleJWT(secret: String) {
    private val algorithm = Algorithm.HMAC256(secret)
    val verifier: JWTVerifier = JWT.require(algorithm).build()
    fun sign(email: String): String = JWT.create().withClaim("email", email).sign(algorithm)
}

@Serializable
data class Token(@SerialName("token") val token: String)

fun Token.asString() = Json {}.encodeToString(Token.serializer(), this)

fun String.asToken() = Json {}.decodeFromString(Token.serializer(), this)

fun Application.authModule() {

    val simpleJwt = SimpleJWT("my-super-secret-for-jwt")

    val users = Collections.synchronizedMap(
        listOf(User("test@mail.com", "testUserName"))
            .associateBy { it.email }
            .toMutableMap()
    )

    install(Authentication) {
        jwt {
            verifier(simpleJwt.verifier)
            validate {
                UserIdPrincipal(it.payload.getClaim("email").asString())
            }
            authSchemes()
        }
    }

    routing {
        post("/auth") {
            val post = call.receive<User>()
            val user = users[post.email] ?: throw InvalidCredentialsException("user doesn't exists")
            if (user.name != post.name) throw InvalidCredentialsException("name doesn't match")
            call.respond(Token(simpleJwt.sign(user.name)).asString())
        }
    }
}