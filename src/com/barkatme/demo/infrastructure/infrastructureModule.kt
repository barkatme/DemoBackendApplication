package com.barkatme.demo.infrastructure

import com.barkatme.demo.infrastructure.model.SimpleJWT
import org.koin.dsl.module


val infrastructureModule = module {
    factory { SimpleJWT("Bearer") }
}