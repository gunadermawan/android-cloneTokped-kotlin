package com.gunder.tokped.core.di

import com.gunder.tokped.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{LoginViewModel(get())}
}