package com.gunder.tokped.core.di

import com.gunder.tokped.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{AuthViewModel(get())}
}