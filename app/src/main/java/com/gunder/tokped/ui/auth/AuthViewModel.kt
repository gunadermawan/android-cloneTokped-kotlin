package com.gunder.tokped.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gunder.tokped.core.data.repository.AppRepository
import com.gunder.tokped.core.data.source.remote.request.LoginRequest
import com.gunder.tokped.core.data.source.remote.request.RegisterRequest

class AuthViewModel(val repo: AppRepository) : ViewModel() {

    fun login(data: LoginRequest) = repo.login(data).asLiveData()
    fun register(data: RegisterRequest) = repo.register(data).asLiveData()
}