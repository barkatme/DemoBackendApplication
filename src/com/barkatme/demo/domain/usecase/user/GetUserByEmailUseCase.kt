package com.barkatme.demo.domain.usecase.user

import com.barkatme.demo.domain.model.User
import com.barkatme.demo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserByEmailUseCase(private val userRepository: UserRepository) {
    suspend fun getUser(email: String): User? = withContext(Dispatchers.IO) {
        userRepository.getByEmail(email)
    }
}