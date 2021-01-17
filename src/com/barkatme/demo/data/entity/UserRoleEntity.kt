package com.barkatme.demo.data.entity

import com.barkatme.demo.data.tables.UserRoles
import com.barkatme.demo.domain.model.Role
import org.jetbrains.exposed.sql.ResultRow


data class UserRoleEntity(
    val id: Int = 0,
    val name: String,
    val createdAt: String? = null,
)

fun ResultRow.asUserRoleEntity() = UserRoleEntity(
    id = get(UserRoles.id),
    name = get(UserRoles.name),
    createdAt = get(UserRoles.createdAt).toString()
)

fun ResultRow.asUserRole() = asUserRoleEntity().asUserRole()

fun UserRoleEntity.asUserRole() = Role(
    id = id,
    name = name,
    createdAt = createdAt
)

fun Role.asUserRoleEntity() = UserRoleEntity(
    id = id,
    name = name,
    createdAt = createdAt
)