package com.barkatme.demo.data.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object UserRoles : Table("USER_ROLES") {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val name: Column<String> = varchar("name", 100)
    val image: Column<String> = varchar("image", 100)
    val createdAt: Column<DateTime> = datetime("created_at")
}