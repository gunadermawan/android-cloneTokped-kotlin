package com.gunder.tokped.utils

import com.chibatching.kotpref.KotprefModel

object Prefs : KotprefModel() {
    var isLogin by booleanPref(false)
}