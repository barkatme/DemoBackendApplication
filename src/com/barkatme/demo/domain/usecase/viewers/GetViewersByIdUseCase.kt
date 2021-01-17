package com.barkatme.demo.domain.usecase.viewers

import com.barkatme.demo.domain.Urls
import com.barkatme.demo.domain.repository.PermissionRepository
import com.barkatme.demo.domain.repository.RoleRepository
import com.barkatme.demo.domain.repository.UserRelationRepository
import com.barkatme.demo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetViewersByIdUseCase(
    private val userRelationRepository: UserRelationRepository,
    private val userRepository: UserRepository,
    private val permissionRepository: PermissionRepository,
    private val roleRepository: RoleRepository
) {
    suspend fun getViewers(id: Int, requesterEmail: String?) = withContext(Dispatchers.IO) {
        val currentUser = requesterEmail?.let { userRepository.getByEmail(it) } ?: throw Exception("invalid email")
        val permissions = permissionRepository.getUrlPermissions(Urls.viewersById)
        val rolePermissions = roleRepository.getPermissions(currentUser.role)
        if (rolePermissions.containsAll(permissions)) {
            userRelationRepository.getViewers(id)
        } else {
            throw Exception("permission denied")
        }
    }
}