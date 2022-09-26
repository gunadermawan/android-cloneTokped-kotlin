package com.gunder.tokped.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gunder.tokped.NavigationActivity
import com.gunder.tokped.core.data.source.remote.network.State
import com.gunder.tokped.core.data.source.remote.request.LoginRequest
import com.gunder.tokped.databinding.ActivityLoginBinding
import com.inyongtisto.myhelper.extension.dismisLoading
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showLoading
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
            login()
        }
    }

    private fun login() {
        if (binding.edtEmail.text?.isEmpty() == true) {
            binding.edtEmail.error = "field is required"
        } else if (binding.edtPassword.text?.isEmpty() == true) {
            binding.edtPassword.error = "field is required"
        }
        val body =
            LoginRequest(binding.edtEmail.text.toString(), binding.edtPassword.text.toString())
        viewModel.login(body).observe(this) {
            when (it.state) {
                State.LOADING -> {
                    showLoading()
                }
                State.SUCCESS -> {
                    dismisLoading()
                    Toast.makeText(this, "Welcome back " + it.data?.name, Toast.LENGTH_SHORT).show()
                    pushActivity(NavigationActivity::class.java)
                }
                State.ERROR -> {
                    dismisLoading()
                    Toast.makeText(this, "Something went wrong:  " + it.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }
}