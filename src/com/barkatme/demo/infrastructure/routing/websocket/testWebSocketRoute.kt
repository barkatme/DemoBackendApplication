package com.barkatme.demo.infrastructure.routing.websocket

import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.mapNotNull

fun Routing.chatWebSocket() {
    webSocket("/chat") { // websocketSession
        incoming.consumeAsFlow().mapNotNull { it as? Frame.Text }.collect { frame ->
            val text = frame.readText()
            print("incoming message: $text")
            outgoing.send(Frame.Text("YOU SAID $text"))
            if (text.equals("bye", ignoreCase = true)) {
                close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE"))
            }
        }
    }
}