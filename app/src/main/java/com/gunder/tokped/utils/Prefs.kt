package com.gunder.tokped.utils

import com.chibatching.kotpref.KotprefModel
import com.gunder.tokped.core.data.source.remote.model.UserResponse
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel

object Prefs : KotprefModel() {
    var isLogin by booleanPref(false)
    var user by stringPref()

    fun setUser(data: UserResponse?) {
        user = data.toJson()
    }

    fun getUser(): UserResponse? {
        if (user.isEmpty()) return null
        return user.toModel(UserResponse::class.java)
    }
}