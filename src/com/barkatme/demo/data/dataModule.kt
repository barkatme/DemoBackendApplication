package com.barkatme.demo.data

import com.barkatme.demo.data.repostiory.*
import com.barkatme.demo.domain.repository.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy


@ExperimentalCoroutinesApi
@FlowPreview
val dataModule = module {

    //heroku repositories
    singleBy<UserRelationRepository, HerokuUserRelationRepository>()
    singleBy<UserRepository, HerokuUserRepository>()
    singleBy<RoleRepository, HerokuUserRoleRepository>()
    singleBy<PermissionRepository, HerokuPermissionRepository>()
    singleBy<ChatRepository, HerokuChatRepository>()

}