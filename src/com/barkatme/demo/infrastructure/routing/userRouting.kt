package com.barkatme.demo.infrastructure.routing

import com.barkatme.demo.domain.Urls
import com.barkatme.demo.domain.usecase.user.GetUserByEmailUseCase
import com.barkatme.demo.infrastructure.extentions.getUserIdPrincipal
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.get

fun Routing.userRouting() {

    val getUserByEmailUseCase: GetUserByEmailUseCase = get()

    authenticate {
        post(Urls.currentUser) {
            val currentUser = call.getUserIdPrincipal()?.name?.let { email -> getUserByEmailUseCase.getUser(email) }
                ?: throw Exception("invalid token")
            call.respond(currentUser)
        }
    }
}