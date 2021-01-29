package com.barkatme.demo.domain.usecase.chat

import com.barkatme.demo.domain.model.Message
import com.barkatme.demo.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class ListenChatMessagesUseCase(private val chatRepository: ChatRepository) {
    fun chatFlow(chatId: Int): Flow<Message> = chatRepository.messagesFlow(chatId)
}