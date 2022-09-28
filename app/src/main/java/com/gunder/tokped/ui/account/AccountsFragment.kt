package com.gunder.tokped.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gunder.tokped.databinding.FragmentAccountsBinding
import com.gunder.tokped.ui.auth.LoginActivity
import com.gunder.tokped.utils.Prefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.pushActivity

class AccountsFragment : Fragment() {

    private var _binding: FragmentAccountsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val notificationsViewModel = ViewModelProvider(this)[AccountsViewModel::class.java]

        _binding = FragmentAccountsBinding.inflate(inflater, container, false)
        setUser()
        logout()
        return binding.root
    }

    private fun logout() {
        binding.btnLogout.setOnClickListener {
            Prefs.isLogin = false
            pushActivity(LoginActivity::class.java)
        }
    }

    private fun setUser() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                tvUsername.text = user.name
                tvPhone.text = user.phone
                tvEmail.text = user.email
                tvIvProfile.text = user.name.getInitial()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}