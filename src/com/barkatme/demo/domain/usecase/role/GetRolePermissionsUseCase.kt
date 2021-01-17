package com.barkatme.demo.domain.usecase.role

import com.barkatme.demo.domain.model.Permission
import com.barkatme.demo.domain.repository.RoleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRolePermissionsUseCase(
    private val roleRepository: RoleRepository
) {
    suspend fun getUserPermissions(roleId: Int): List<Permission> = withContext(Dispatchers.IO) {
        roleRepository.getPermissions(roleId)
    }
}