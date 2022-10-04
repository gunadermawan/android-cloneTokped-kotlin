package com.gunder.tokped.ui.update

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gunder.tokped.core.data.source.remote.request.UpdateProfileRequest
import com.gunder.tokped.databinding.ActivityUpdateProfileBinding
import com.gunder.tokped.ui.auth.AuthViewModel
import com.gunder.tokped.utils.Prefs
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.setToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateProfileActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private lateinit var binding: ActivityUpdateProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainButton()
        setData()
        setToolbar(binding.toolbar, "Update Profile")
    }

    private fun setData() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                edtUsername.setText(user.name)
                edtEmail.setText(user.email)
                edtPhone.setText(user.phone)
            }
        }
    }

    private fun mainButton() {
        binding.btnSave.setOnClickListener {
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
        }

        val body = UpdateProfileRequest(
            1,
            binding.edtUsername.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPhone.text.toString(),
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
        onBackPressed()
    }

}