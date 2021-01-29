package com.barkatme.demo.domain

import com.barkatme.demo.domain.usecase.auth.SignInUseCase
import com.barkatme.demo.domain.usecase.auth.SignOutUseCase
import com.barkatme.demo.domain.usecase.auth.SignUpUseCase
import com.barkatme.demo.domain.usecase.chat.ListenChatMessagesUseCase
import com.barkatme.demo.domain.usecase.chat.NewMessageUseCase
import com.barkatme.demo.domain.usecase.role.GetRolePermissionsUseCase
import com.barkatme.demo.domain.usecase.role.GetUserRoleListUseCase
import com.barkatme.demo.domain.usecase.role.GetUserRolePermissionsUseCase
import com.barkatme.demo.domain.usecase.user.GetOtherUserUseCase
import com.barkatme.demo.domain.usecase.user.GetUserByEmailUseCase
import com.barkatme.demo.domain.usecase.user.GetUserByTokenUseCase
import com.barkatme.demo.domain.usecase.viewers.DeleteViewersByIdUseCase
import com.barkatme.demo.domain.usecase.viewers.DeleteViewersUseCase
import com.barkatme.demo.domain.usecase.viewers.GetViewersByIdUseCase
import com.barkatme.demo.domain.usecase.viewers.GetViewersUseCase
import org.koin.dsl.module


val domainModule = module {

    //auth
    single { SignInUseCase(get()) }
    single { SignUpUseCase(get()) }
    single { SignOutUseCase(get()) }

    //user
    single { GetUserByEmailUseCase(get()) }
    single { GetOtherUserUseCase(get(), get()) }
    single { GetUserByTokenUseCase(get()) }

    //rolePermissions
    single { GetUserRolePermissionsUseCase(get(), get()) }
    single { GetRolePermissionsUseCase(get()) }

    //viewers
    single { GetViewersUseCase(get(), get()) }
    single { GetViewersByIdUseCase(get(), get(), get(), get()) }
    single { DeleteViewersByIdUseCase(get(), get(), get(), get()) }
    single { DeleteViewersUseCase(get(), get(), get(), get()) }

    //chat
    single { ListenChatMessagesUseCase(get()) }
    single { NewMessageUseCase(get()) }

    //others (admin/additional info requests)
    single { GetUserRoleListUseCase(get()) }
}