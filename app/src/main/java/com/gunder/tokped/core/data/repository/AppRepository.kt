package com.gunder.tokped.core.data.repository

import android.util.Log
import com.gunder.tokped.core.data.source.local.LocalDataSource
import com.gunder.tokped.core.data.source.remote.RemoteDataSource
import com.gunder.tokped.core.data.source.remote.request.LoginRequest
import kotlinx.coroutines.flow.flow

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {
    fun login(data:LoginRequest) = flow {
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    if (body != null) {
                        emit(body)
                    }
                    Log.i(LOGIN, body.toString())
                } else {
                    Log.e(LOGIN, it.message())
                }
            }

        } catch (e: Exception) {
            Log.d(LOGIN, "Login error: ${e.message}")
        }
    }

    private companion object {
        const val LOGIN = "LOGIN"
    }
}