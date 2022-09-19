package com.gunder.tokped.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gunder.tokped.core.data.source.remote.request.LoginRequest
import com.gunder.tokped.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    private fun setData() {
        viewModel.text.observe(this) {
            binding.edtEmail.setText(it)
        }
        binding.btnLogin.setOnClickListener {
            val body =
                LoginRequest(binding.edtEmail.text.toString(), binding.edtPassword.text.toString())
            viewModel.login(body).observe(this) {

            }
        }
    }
}