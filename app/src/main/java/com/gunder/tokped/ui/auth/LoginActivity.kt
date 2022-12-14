package com.gunder.tokped.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gunder.tokped.NavigationActivity
import com.gunder.tokped.core.data.source.remote.network.State
import com.gunder.tokped.core.data.source.remote.request.LoginRequest
import com.gunder.tokped.databinding.ActivityLoginBinding
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainButton()
    }

    private fun mainButton() {
        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.textViewCondition.setOnClickListener {
            intentActivity(RegisterActivity::class.java)
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
                    binding.pb.visibility = View.VISIBLE
                }
                State.SUCCESS -> {
                    binding.pb.visibility = View.GONE
                    Toast.makeText(this, "Welcome back " + it.data?.name, Toast.LENGTH_SHORT).show()
                    pushActivity(NavigationActivity::class.java)
                }
                State.ERROR -> {
                    binding.pb.visibility = View.GONE
                    Toast.makeText(this, "Something went wrong:  " + it.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }
}