package com.gunder.tokped.core.data.repository

import android.util.Log
import com.gunder.tokped.core.data.source.local.LocalDataSource
import com.gunder.tokped.core.data.source.remote.RemoteDataSource
import com.gunder.tokped.core.data.source.remote.network.Resource
import com.gunder.tokped.core.data.source.remote.request.LoginRequest
import com.gunder.tokped.utils.Prefs
import com.inyongtisto.myhelper.extension.getErrorBody
import kotlinx.coroutines.flow.flow

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {
    fun login(data: LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    if (body != null) {
                        emit(Resource.success(user))
                    }
                    Log.i(LOGIN, body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "default error", null))
                    Log.e(LOGIN, "Error: Login Failed because ${it.message()}")
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