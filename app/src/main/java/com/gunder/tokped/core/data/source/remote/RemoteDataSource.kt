package com.gunder.tokped.core.data.source.remote

import com.gunder.tokped.core.data.source.remote.network.ApiService
import com.gunder.tokped.core.data.source.remote.request.LoginRequest
import com.gunder.tokped.core.data.source.remote.request.RegisterRequest

class RemoteDataSource(private val api: ApiService) {
    suspend fun login(data: LoginRequest) = api.login(data)
    suspend fun register(data: RegisterRequest) = api.register(data)
}