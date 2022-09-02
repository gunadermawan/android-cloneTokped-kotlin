package com.gunder.tokped.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gunder.tokped.core.data.repository.AppRepository

class LoginViewModel(val repo: AppRepository) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Hi, i'm developer"
    }
    val text: LiveData<String> = _text

    fun setData() {
        _text.postValue("Hi, i'm android dev")
    }
}