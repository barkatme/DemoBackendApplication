package com.barkatme.demo.infrastructure.module

import com.auth0.jwt.exceptions.TokenExpiredException
import com.barkatme.demo.data.DatabaseFactory
import com.barkatme.demo.domain.model.Credentials
import com.barkatme.demo.domain.model.User
import com.barkatme.demo.domain.usecase.auth.SignInUseCase
import com.barkatme.demo.domain.usecase.auth.SignOutUseCase
import com.barkatme.demo.domain.usecase.auth.SignUpUseCase
import com.barkatme.demo.domain.usecase.user.GetUserByEmailUseCase
import com.barkatme.demo.infrastructure.extentions.getUserIdPrincipal
import com.barkatme.demo.infrastructure.model.SimpleJWT
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import org.koin.ktor.ext.get
import java.time.Instant
import java.util.*

@KtorExperimentalAPI
fun Application.authModule(@Suppress("UNUSED_PARAMETER") testing: Boolean = false) {
    DatabaseFactory.init()
    val simpleJwt: SimpleJWT = get()

    install(Authentication) {
        jwt {
            verifier(simpleJwt.verifier)
            validate {
                UserIdPrincipal(it.payload.getClaim("name").asString())
            }
            authSchemes()
        }
    }

    routing {

        val signInUseCase: SignInUseCase = get()
        val signUpUseCase: SignUpUseCase = get()
        val signOutUseCase: SignOutUseCase = get()
        val getUserByEmailUseCase: GetUserByEmailUseCase = get()

        post("/sign-in") {
            val post = call.receive<Credentials>()
            val token = signInUseCase.signIn(
                post.email,
                post.password,
                tokenChecker = {
                    try {
                        val decodedJWT = simpleJwt.verifier.verify(it)
                        decodedJWT.expiresAt?.let { date -> date > Date.from(Instant.now()) } ?: false
                    } catch (e: TokenExpiredException) {
                        false
                    }
                },
                tokenGenerator = { simpleJwt.sign(post.email) }
            )
            call.respond(mapOf("token" to token))
        }

        post("/sign-up") {
            val credentials = call.receive<Credentials>()
            val userWithEmailAndPassword = User(email = credentials.email, passwordHash = credentials.password)
            val resultUser = signUpUseCase.signUp(userWithEmailAndPassword) {
                simpleJwt.sign(userWithEmailAndPassword.email)
            }
            call.respond(mapOf("token" to resultUser.token))
        }

        get("/sign-out") {
            val currentUser = call.getUserIdPrincipal()?.name?.let { email -> getUserByEmailUseCase.getUser(email) }
                ?: throw Exception("invalid token")
            val newToken = signOutUseCase.signOut(currentUser.email) { simpleJwt.sign(currentUser.email) }
            call.respond(mapOf("token" to newToken))
        }
    }
}