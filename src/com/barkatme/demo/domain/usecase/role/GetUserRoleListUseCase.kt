package com.barkatme.demo.domain.usecase.role

import com.barkatme.demo.domain.model.Role
import com.barkatme.demo.domain.repository.RoleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserRoleListUseCase(private val roleRepository: RoleRepository) {

    suspend fun getUserRoles(): List<Role> = withContext(Dispatchers.IO) {
        roleRepository.getAll()
    }
}