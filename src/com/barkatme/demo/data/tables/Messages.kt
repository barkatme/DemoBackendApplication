package com.barkatme.demo.data.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object Messages : Table("MESSAGE") {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val sendTime: Column<DateTime> = datetime("send_time")
    val nickName: Column<String> = varchar("nickname", 100)
    val text: Column<String> = varchar("text", 255)
}