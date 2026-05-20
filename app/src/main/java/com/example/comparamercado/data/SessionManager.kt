package com.example.comparamercado.data

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USER_EMAIL = "user_email"
    }

    fun criarSessao(email: String) {
        prefs.edit().putString(KEY_USER_EMAIL, email).apply()
    }

    fun getEmailUtilizador(): String? {
        return prefs.getString(KEY_USER_EMAIL, null)
    }

    fun limparSessao() {
        prefs.edit().remove(KEY_USER_EMAIL).commit()
    }
}