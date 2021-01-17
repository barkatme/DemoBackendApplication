package com.barkatme.demo.infrastructure.extentions

import io.ktor.application.*
import io.ktor.auth.*

fun ApplicationCall.getUserIdPrincipal(): UserIdPrincipal? = principal()

fun ApplicationCall.getToken(): String? =
    request.headers["Authorization"]?.split(" ")?.takeIf { it.size > 1 }?.run { this[1] }

fun ApplicationCall.getParameter(param: String): String? = parameters[param]

fun ApplicationCall.requireParameter(param: String): String =
    getParameter(param) ?: throw Exception("parameter $param not found (required)")
