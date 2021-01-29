package com.barkatme.demo.domain.usecase.chat

import com.barkatme.demo.domain.model.Message
import com.barkatme.demo.domain.repository.ChatRepository

class NewMessageUseCase(private val chatRepository: ChatRepository) {
    suspend fun newMessage(message: Message) = chatRepository.newMessage(message)
}