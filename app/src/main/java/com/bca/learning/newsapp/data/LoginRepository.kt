package com.bca.learning.newsapp.data

import android.content.Context
import android.util.Base64
import com.bca.learning.newsapp.api.LoginService
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.io.IOException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.inject.Inject

class LoginRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val loginService: LoginService
) {

    private fun encryptToken(token: String): String {
        val plaintext: ByteArray = token.toByteArray()
        val keygen = KeyGenerator.getInstance("AES")
        keygen.init(256)
        val key = keygen.generateKey()
        val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val ciphertext = cipher.doFinal(plaintext)
        return Base64.encodeToString(ciphertext, Base64.DEFAULT)
    }

    suspend fun login(email: String, password: String): String? {
        try {
            val loginResponse = loginService.login(LoginService.API_KEY, email, password)
            if (loginResponse.status) {
                val sharedPreferences = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                sharedPreferences.edit().apply() {
                    putString("TOKEN", encryptToken(loginResponse.token!!))
                }
                return loginResponse.token
            }
            throw Exception(loginResponse.message)
        } catch (e: HttpException) {
            if (e.code() == 406 || e.code() == 403) {
                throw Exception("Wrong email or password")
            }
            throw Exception("Internet error")
        } catch (e: IOException) {
            throw Exception("Parsing Error")
        }
    }
}