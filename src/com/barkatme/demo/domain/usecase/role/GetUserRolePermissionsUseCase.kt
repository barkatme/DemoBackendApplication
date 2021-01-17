package com.barkatme.demo.domain.usecase.role

import com.barkatme.demo.domain.model.Permission
import com.barkatme.demo.domain.repository.RoleRepository
import com.barkatme.demo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class GetUserRolePermissionsUseCase(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
) {
    suspend fun getUserPermissions(userEmail: String): List<Permission> = withContext(Dispatchers.IO) {
        val role = userRepository.getByEmail(userEmail)?.role ?: throw Exception("Invalid email")
        roleRepository.getPermissions(role)
    }
}