package com.barkatme.demo.domain.repository

import com.barkatme.demo.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getMessages(chatId: Int): List<Message>
    fun messagesFlow(chatId: Int): Flow<Message>
    suspend fun newMessage(message: Message)
}