package com.barkatme.demo.domain.usecase.user

import com.barkatme.demo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserByTokenUseCase(private val userRepository: UserRepository) {
    suspend fun getUser(token: String) = withContext(Dispatchers.IO) {
        userRepository.getByToken(token)
    }
}