package com.barkatme.demo.data.entity

import com.barkatme.demo.data.tables.Permissions
import com.barkatme.demo.domain.model.Permission
import org.jetbrains.exposed.sql.ResultRow


data class PermissionEntity(
    val id: Int = 0,
    val name: String,
    val createdAt: String? = null,
)

fun ResultRow.asPermissionEntity() = PermissionEntity(
    id = get(Permissions.id),
    name = get(Permissions.name),
    createdAt = get(Permissions.createdAt).toString()
)

fun ResultRow.asPermission() = asPermissionEntity().asPermission()

fun PermissionEntity.asPermission() = Permission(
    id = id,
    name = name,
    createdAt = createdAt
)

fun Permission.asPermissionEntity() = PermissionEntity(
    id = id,
    name = name,
    createdAt = createdAt
)