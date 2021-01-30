package com.barkatme.demo.infrastructure.routing.websocket

import com.barkatme.demo.domain.model.asMessage
import com.barkatme.demo.domain.model.asString
import com.barkatme.demo.domain.usecase.chat.ListenChatMessagesUseCase
import com.barkatme.demo.domain.usecase.chat.NewMessageUseCase
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.ktor.ext.get

fun Routing.chatWebSocket() {

    val newMessageUseCase: NewMessageUseCase = get()
    val listenChatMessagesUseCase: ListenChatMessagesUseCase = get()

    val users = mutableListOf<WebSocketSession>()

    CoroutineScope(Dispatchers.IO).launch {
        listenChatMessagesUseCase.chatFlow(0).collect { message ->
            users.forEach { user ->
                if (user.isActive) {
                    val frame = Frame.byType(true, FrameType.TEXT, message.asString().toByteArray())
                    user.outgoing.send(frame)
                }
            }
        }
    }

    webSocket("/chat") { // websocketSession
        users.add(this)
        incoming.consumeAsFlow()
            .mapNotNull { it as? Frame.Text }
            .collect { frame ->
                val message = frame.readText().also {
                    println("incoming message: $it")
                }.asMessage()
                newMessageUseCase.newMessage(message)
            }
    }
}