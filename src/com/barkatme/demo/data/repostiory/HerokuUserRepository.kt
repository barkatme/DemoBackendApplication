package com.barkatme.demo.data.repostiory

import com.barkatme.demo.data.dbQuery
import com.barkatme.demo.data.entity.asUser
import com.barkatme.demo.data.entity.asUserEntity
import com.barkatme.demo.data.tables.Users
import com.barkatme.demo.domain.model.User
import com.barkatme.demo.domain.repository.UserRepository
import org.jetbrains.exposed.sql.*


class HerokuUserRepository : UserRepository {

    override suspend fun getByEmail(email: String): User? = dbQuery {
        Users.select { (Users.email eq email) }
            .mapNotNull { it.asUser() }
            .singleOrNull()
    }

    override suspend fun getById(id: Int): User? = dbQuery {
        Users.select { (Users.id eq id) }
            .mapNotNull { it.asUser() }
            .singleOrNull()
    }

    override suspend fun insert(user: User): Int = dbQuery {
        Users.insert {
            it[email] = user.email
            it[passwordHash] = user.passwordHash
            it[login] = user.login
            it[about] = user.about
            it[first_name] = user.firstName
            it[last_name] = user.lastName
            it[active] = user.isOnline
            it[token] = user.token
        } get Users.id
    }

    override suspend fun update(user: User): Int = dbQuery {
        val entity = user.asUserEntity()
        Users.update(where = { Users.id eq user.id }) {
            it[email] = entity.email
            it[passwordHash] = entity.passwordHash
            it[login] = entity.login
            it[about] = entity.about
            it[first_name] = entity.firstName
            it[last_name] = entity.lastName
            it[active] = entity.isOnline
            it[token] = entity.token
        }
    }

    override suspend fun delete(user: User): Int = dbQuery {
        Users.deleteWhere {
            Users.id eq user.id
        }
    }

    override suspend fun delete(id: Int): Int = dbQuery {
        Users.deleteWhere {
            Users.id eq id
        }
    }

    override suspend fun count(): Int = dbQuery {
        Users.selectAll().count()
    }

    override suspend fun recent(): List<User> = dbQuery {
        Users.selectAll().orderBy(Users.date_registration, SortOrder.ASC).limit(10)
            .mapNotNull { it.asUserEntity().asUser() }
    }

    override suspend fun getByToken(token: String): User? = dbQuery {
        Users.select { (Users.token eq token) }
            .mapNotNull { it.asUser() }
            .singleOrNull()
    }
}