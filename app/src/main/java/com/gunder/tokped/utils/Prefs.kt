package com.gunder.tokped.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class Prefs(activity: Activity) {
    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = activity.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)
    }

    fun setIsLogin(value: Boolean) {
        sharedPreferences?.edit()?.putBoolean(IS_LOGIN, value)?.apply()
    }

    fun getIsLogin(): Boolean? {
        return sharedPreferences?.getBoolean(IS_LOGIN, false)
    }

    companion object {
        private const val IS_LOGIN = "IS_LOGIN"
        private const val MY_PREF = "MY_PREF"
    }
}