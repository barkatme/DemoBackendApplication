package com.barkatme.demo.data.repostiory

import com.barkatme.demo.data.dbQuery
import com.barkatme.demo.data.entity.asMessage
import com.barkatme.demo.data.entity.asMessageEntity
import com.barkatme.demo.data.tables.Messages
import com.barkatme.demo.domain.model.Message
import com.barkatme.demo.domain.repository.ChatRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll


@FlowPreview
@ExperimentalCoroutinesApi
class HerokuChatRepository : ChatRepository {
    private val msgFlow: MutableStateFlow<Message> = MutableStateFlow(Message())
    private val sendScope = CoroutineScope(Dispatchers.IO)

    override suspend fun getMessages(chatId: Int): List<Message> =
        dbQuery {
            Messages.selectAll()
                .map { it.asMessage() }
        }

    override fun messagesFlow(chatId: Int): Flow<Message> = msgFlow

    override suspend fun newMessage(message: Message) {
        val entity = message.asMessageEntity()
        val id = dbQuery {
            Messages.insert {
                it[nickName] = entity.nickName
                it[text] = entity.text
            } get Messages.id
        }
        val sendMessage = message.copy(id = id)
        sendScope.launch {
            msgFlow.emit(sendMessage)
        }
    }
}