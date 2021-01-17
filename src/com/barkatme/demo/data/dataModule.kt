package com.barkatme.demo.data

import com.barkatme.demo.data.repostiory.HerokuPermissionRepository
import com.barkatme.demo.data.repostiory.HerokuUserRelationRepository
import com.barkatme.demo.data.repostiory.HerokuUserRepository
import com.barkatme.demo.data.repostiory.HerokuUserRoleRepository
import com.barkatme.demo.domain.repository.PermissionRepository
import com.barkatme.demo.domain.repository.RoleRepository
import com.barkatme.demo.domain.repository.UserRelationRepository
import com.barkatme.demo.domain.repository.UserRepository
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy


val dataModule = module {

    //heroku repositories
    singleBy<UserRelationRepository, HerokuUserRelationRepository>()
    singleBy<UserRepository, HerokuUserRepository>()
    singleBy<RoleRepository, HerokuUserRoleRepository>()
    singleBy<PermissionRepository, HerokuPermissionRepository>()

}