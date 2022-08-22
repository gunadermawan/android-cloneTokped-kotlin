package com.gunder.tokped.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gunder.tokped.databinding.ActivityLoginBinding
import com.gunder.tokped.utils.Prefs

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        val sharedPreferences = Prefs(this)
        if (sharedPreferences.getIsLogin() == true) {
            loginBinding.tvLogin.visibility = View.VISIBLE
            loginBinding.tvLogout.visibility = View.INVISIBLE
        } else {
            loginBinding.tvLogout.visibility = View.VISIBLE
            loginBinding.tvLogin.visibility = View.INVISIBLE
        }
        loginBinding.btnStatus.setOnClickListener {
            sharedPreferences.setIsLogin(true)
            onBackPressed()
        }
        loginBinding.btnStatus.setOnClickListener {
            sharedPreferences.setIsLogin(false)
            onBackPressed()
        }
    }
}