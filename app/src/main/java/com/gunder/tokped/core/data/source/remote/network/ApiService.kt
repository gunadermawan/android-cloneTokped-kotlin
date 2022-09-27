package com.gunder.tokped.core.data.source.remote.network

import com.gunder.tokped.core.data.source.remote.request.LoginRequest
import com.gunder.tokped.core.data.source.remote.request.RegisterRequest
import com.gunder.tokped.core.data.source.remote.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body login: LoginRequest,
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body register: RegisterRequest,
    ): Response<LoginResponse>

}