package com.barkatme.demo.infrastructure.module

import io.ktor.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.websocket.*
import java.time.Duration


fun Application.webSocketsModule() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(60) // Disabled (null) by default

        maxFrameSize = Long.MAX_VALUE // Disabled (max value). The connection will be closed if surpassed this length.
        masking = false
    }
}