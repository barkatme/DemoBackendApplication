package com.barkatme.demo.domain.usecase.viewers

import com.barkatme.demo.domain.repository.UserRelationRepository
import com.barkatme.demo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetViewersUseCase(
    private val userRelationRepository: UserRelationRepository,
    private val userRepository: UserRepository
) {
    suspend fun getViewedUsers(email: String?) = withContext(Dispatchers.IO) {
        val currentUser = email?.let { userRepository.getByEmail(it) } ?: throw Exception("invalid email")
        userRelationRepository.getViewers(currentUser.id)
    }
}