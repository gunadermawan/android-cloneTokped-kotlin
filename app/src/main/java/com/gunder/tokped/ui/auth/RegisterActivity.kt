package com.gunder.tokped.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gunder.tokped.NavigationActivity
import com.gunder.tokped.core.data.source.remote.network.State
import com.gunder.tokped.core.data.source.remote.request.RegisterRequest
import com.gunder.tokped.databinding.ActivityRegisterBinding
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    private fun setData() {
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        if (binding.edtUsername.isEmpty()) {
            binding.edtUsername.error = "field is required"
        } else if (binding.edtEmail.error?.isEmpty() == true) {
            binding.edtEmail.error = "field is required"
        } else if (binding.edtPhone.text?.isEmpty() == true) {
            binding.edtEmail.error = "field is required"
        } else if (binding.edtPassword.text?.isEmpty() == true) {
            binding.edtPassword.error = "field is required"
        }

        val body = RegisterRequest(binding.edtUsername.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPhone.text.toString(),
            binding.edtPassword.text.toString())
        viewModel.register(body).observe(this) {
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