package com.bca.learning.newsapp.ui

import androidx.lifecycle.MutableLiveData
import com.bca.learning.newsapp.data.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginRepository: LoginRepository
) {

    val snackbar = MutableLiveData<String>()



}