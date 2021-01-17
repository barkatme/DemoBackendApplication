package com.barkatme.demo.domain.usecase.user

import com.barkatme.demo.domain.model.User
import com.barkatme.demo.domain.repository.UserRelationRepository
import com.barkatme.demo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetOtherUserUseCase(
    private val userRepository: UserRepository,
    private val userRelationRepository: UserRelationRepository
) {
    suspend fun getUser(currentUserId: Int?, userId: Int): User? = withContext(Dispatchers.IO) {
        val currentUser = currentUserId?.let { userRepository.getById(it) }
        val user = userRepository.getById(userId)
        if (currentUser != null && user != null && currentUser.id != user.id) {
            userRelationRepository.viewUser(currentUser.id, user.id)
        }
        return@withContext user
    }
}