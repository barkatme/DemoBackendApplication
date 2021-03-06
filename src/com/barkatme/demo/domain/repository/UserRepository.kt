package com.barkatme.demo.domain.repository

import com.barkatme.demo.domain.model.User

interface UserRepository {
    suspend fun getByEmail(email: String): User?
    suspend fun getById(id: Int): User?
    suspend fun insert(user: User): Int
    suspend fun update(user: User): Int
    suspend fun delete(user: User): Int
    suspend fun delete(id: Int): Int
    suspend fun count(): Int
    suspend fun recent(): List<User>
    suspend fun getByToken(token: String): User?
}