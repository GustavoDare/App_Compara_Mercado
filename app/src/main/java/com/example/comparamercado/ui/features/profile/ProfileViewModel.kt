package com.example.comparamercado.ui.features.profile

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.comparamercado.data.SessionManager

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionManager = SessionManager(application)

    var emailUtilizado = mutableStateOf("")
        private set

    init {
        emailUtilizado.value = sessionManager.getEmailUtilizador() ?: ""
    }

    fun fecharSessao() {
        sessionManager.limparSessao()
    }
}