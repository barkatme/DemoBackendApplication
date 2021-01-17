package com.barkatme.demo.domain.usecase.auth

import com.barkatme.demo.domain.model.User
import com.barkatme.demo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignOutUseCase(private val userRepository: UserRepository) {

    suspend fun signOut(
        email: String,
        tokenGenerator: (User) -> String
    ): String =
        withContext(Dispatchers.IO) {
            val user = userRepository.getByEmail(email) ?: throw Exception("auth exception")
            val newToken = tokenGenerator(user)
            user.token = newToken
            userRepository.update(user)
            newToken
        }
}