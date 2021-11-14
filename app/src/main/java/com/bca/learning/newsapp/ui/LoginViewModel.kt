package com.bca.learning.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.bca.learning.newsapp.data.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

    val snackbar = MutableLiveData<String>()

    fun login(email: String, password: String) = viewModelScope.launch {
        try {
            if (email.isNullOrBlank() || password.isNullOrBlank()) {
                throw Exception("Email and password can't be empty")
            }
            val token = loginRepository.login(email, password)
            if (token != null) {
                snackbar.postValue("Login success")
            }
        } catch (ex: Exception) {
            snackbar.postValue(ex.message)
        }
    }

    fun logout() = viewModelScope.launch {
        snackbar.postValue("Logout")
    }

}