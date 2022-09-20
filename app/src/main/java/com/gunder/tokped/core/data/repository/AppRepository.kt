package com.gunder.tokped.core.data.repository

import android.util.Log
import com.gunder.tokped.core.data.source.local.LocalDataSource
import com.gunder.tokped.core.data.source.remote.RemoteDataSource
import com.gunder.tokped.core.data.source.remote.network.Resource
import com.gunder.tokped.core.data.source.remote.request.LoginRequest
import kotlinx.coroutines.flow.flow

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {
    fun login(data: LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    if (body != null) {
                        emit(Resource.success(body.data))
                    }
                    Log.i(LOGIN, body.toString())
                } else {
                    emit(Resource.error(it.body()?.message ?: "default error!", null))
                    Log.e(LOGIN, it.message())
                }
            }

        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "something went wrong!", null))
            Log.d(LOGIN, "Login error: ${e.message}")
        }
    }

    private companion object {
        const val LOGIN = "LOGIN"
    }
}