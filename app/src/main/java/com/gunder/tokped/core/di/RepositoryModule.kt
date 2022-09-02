package com.gunder.tokped.core.di

import com.gunder.tokped.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }
}