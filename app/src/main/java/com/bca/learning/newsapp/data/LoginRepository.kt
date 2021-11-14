package com.bca.learning.newsapp.data

import android.content.Context
import com.bca.learning.newsapp.api.LoginService
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.io.IOException
import javax.crypto.KeyGenerator
import javax.inject.Inject

class LoginRepository @Inject constructor(
    @ApplicationContext val context: Context,
    val loginService: LoginService
) {

    fun encryptToken(token: String): String {
        val plaintext: ByteArray = token.toByteArray()
        val keygen = KeyGenerator.getInstance("AES")

    }

    suspend fun login(email: String, password: String): String? {
        try {
            val loginResponse = loginService.login(LoginService.API_KEY, email, password)
            if (loginResponse.status) {
                val sharedPreferences = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                sharedPreferences.edit().apply() {
                    putString("TOKEN", "")
                }
                return loginResponse.token
            }
            throw Exception(loginResponse.message)
        } catch (e: HttpException) {
            throw Exception("Connection Problem")
        } catch (e: IOException) {
            throw Exception("Parsing Error")
        }
    }
}