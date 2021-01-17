package com.barkatme.demo.domain.usecase.auth

import com.barkatme.demo.domain.model.User
import com.barkatme.demo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.exceptions.ExposedSQLException

class SignUpUseCase(private val userRepository: UserRepository) {

    suspend fun signUp(user: User, tokenGenerator: (User) -> String): User = withContext(Dispatchers.IO) {
        user.token = tokenGenerator(user)
        try {
            userRepository.insert(user)
        } catch (e: ExposedSQLException) {
            throw Exception("email already exists")
        }
        return@withContext user
    }
}