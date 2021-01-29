package com.barkatme.demo.data.entity

import com.barkatme.demo.data.tables.Messages
import com.barkatme.demo.domain.model.Message
import org.jetbrains.exposed.sql.ResultRow

data class MessageEntity(
    val id: Int = 0,
    val nickName: String = "",
    val time: Long = System.currentTimeMillis(),
    val text: String = "",
)

fun MessageEntity.asMessage() = Message(id, nickName, time, text)
fun Message.asMessageEntity() = MessageEntity(id, nickName, time, text)

fun ResultRow.asMessageEntity() = MessageEntity(
    id = get(Messages.id),
    nickName = get(Messages.nickName),
    time = get(Messages.sendTime).millis,
    text = get(Messages.text)
)

fun ResultRow.asMessage() = asMessageEntity().asMessage()