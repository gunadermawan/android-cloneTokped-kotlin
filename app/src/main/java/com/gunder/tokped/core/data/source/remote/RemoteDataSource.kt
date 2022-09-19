package com.gunder.tokped.core.data.source.remote

import com.gunder.tokped.core.data.source.remote.network.ApiService
import com.gunder.tokped.core.data.source.remote.request.LoginRequest

class RemoteDataSource(private val api: ApiService) {
    suspend fun login(data:LoginRequest) = api.login(data)
}