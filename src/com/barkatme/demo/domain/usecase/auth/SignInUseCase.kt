package com.barkatme.demo.domain.usecase.auth

import com.barkatme.demo.domain.model.User
import com.barkatme.demo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignInUseCase(private val userRepository: UserRepository) {

    suspend fun signIn(
        email: String,
        password: String,
        tokenChecker: (String) -> Boolean,
        tokenGenerator: (User) -> String
    ): String =
        withContext(Dispatchers.IO) {
            val user = userRepository.getByEmail(email)
            if (user == null) {
                throw Exception("auth error")
            } else if (password != user.passwordHash) {
                throw Exception("auth invalid password")
            }
            return@withContext user.token?.takeIf { tokenChecker(it) } ?: run {
                val newToken = tokenGenerator(user)
                user.token = newToken
                userRepository.update(user)
                newToken
            }
        }
}