package com.gunder.tokped.core.data.repository

import android.util.Log
import com.gunder.tokped.core.data.source.local.LocalDataSource
import com.gunder.tokped.core.data.source.remote.RemoteDataSource
import kotlinx.coroutines.flow.flow

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {
    fun login() = flow {
        try {
            remote.login().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    if (body != null) {
                        emit(body)
                    }
                    Log.i(LOGIN, body.toString())
                } else {
                    Log.e(LOGIN, "error")
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