package com.barkatme.demo.data.repostiory

import com.barkatme.demo.data.dbQuery
import com.barkatme.demo.data.entity.asUser
import com.barkatme.demo.data.tables.Users
import com.barkatme.demo.data.tables.ViewedUsers
import com.barkatme.demo.domain.repository.UserRelationRepository
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime


class HerokuUserRelationRepository : UserRelationRepository {
    override suspend fun viewUser(userId: Int, viewedUserId: Int): Int? = dbQuery {
        ViewedUsers.update(where = {
            (ViewedUsers.userId eq userId) and (ViewedUsers.viewedUserId eq viewedUserId)
        }) {
            it[lastTime] = DateTime.now()
        }.takeIf { it == 0 }?.run {
            ViewedUsers.insert {
                it[ViewedUsers.userId] = userId
                it[ViewedUsers.viewedUserId] = viewedUserId
                it[lastTime] = DateTime.now()
            } get ViewedUsers.userId
        }
    }

    override suspend fun getViewers(userId: Int) = dbQuery {
        (ViewedUsers.leftJoin(Users, { ViewedUsers.userId }, { id })).slice(
            ViewedUsers.lastTime,
            Users.id,
            Users.login,
            Users.token,
            Users.first_name,
            Users.last_name,
            Users.about,
            Users.email,
            Users.deleted,
            Users.active,
            Users.date_registration,
            Users.userRoleId,
            Users.passwordHash,
        ).select {
            ViewedUsers.viewedUserId eq userId
        }.map {
            UserRelationRepository.ViewerResult(it.asUser(), it[ViewedUsers.lastTime].toString())
        }
    }

    override suspend fun deleteViewer(viewerId: Int, userId: Int): Int = dbQuery {
        ViewedUsers.deleteWhere { (ViewedUsers.userId eq userId) and (ViewedUsers.viewedUserId eq viewerId) }
    }

    override suspend fun deleteViewers(userId: Int): Int = dbQuery {
        ViewedUsers.deleteWhere { ViewedUsers.viewedUserId eq userId }
    }
}