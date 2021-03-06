package com.barkatme.demo.domain.repository

import com.barkatme.demo.domain.model.Permission
import com.barkatme.demo.domain.model.Role

interface RoleRepository {

    suspend fun getAll(): List<Role>
    suspend fun getById(id: Int): Role?
    suspend fun insert(role: Role): Int
    suspend fun update(role: Role): Int
    suspend fun delete(role: Role): Int
    suspend fun addPermission(role: Role, permission: Permission): Int
    suspend fun getPermissions(userRoleId: Int): List<Permission>
    suspend fun getPermissions(role: Role): List<Permission>
    suspend fun checkPermission(role: Role, permission: Permission): Boolean
    suspend fun removePermission(role: Role, permission: Permission): Int
    suspend fun delete(id: Int): Int
    suspend fun deleteAll(): Int
}