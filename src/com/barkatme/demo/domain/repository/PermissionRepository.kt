package com.barkatme.demo.domain.repository

import com.barkatme.demo.domain.model.Permission

interface PermissionRepository {
    suspend fun getAll(): List<Permission>
    suspend fun getById(id: Int): Permission?
    suspend fun getUrlPermissions(url: String): List<Permission>
    suspend fun insert(permission: Permission): Int
    suspend fun update(permission: Permission): Int
    suspend fun delete(permission: Permission): Int
    suspend fun delete(id: Int): Int
    suspend fun deleteAll(): Int
}