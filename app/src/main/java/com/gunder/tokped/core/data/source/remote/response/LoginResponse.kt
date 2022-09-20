package com.gunder.tokped.core.data.source.remote.response

import com.gunder.tokped.core.data.source.remote.model.UserResponse

data class LoginResponse(
    val code: Int? = null,
    val message: String? = null,
    val data: UserResponse? = null,
)