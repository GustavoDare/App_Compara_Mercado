package com.example.comparamercado.ui.features.login

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comparamercado.data.AppDatabase
import com.example.comparamercado.data.SessionManager
import com.example.comparamercado.data.UsuarioRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UsuarioRepository
    private val sessionManager = SessionManager(application)
    var email = mutableStateOf("")
        private set
    var senha = mutableStateOf("")
        private set
    var erroLogin = mutableStateOf(false)
        private set

    init {
        val dao = AppDatabase.getDatabase(application).usuarioDao()
        repository = UsuarioRepository(dao)
    }

    fun onEmailChange(novoEmail: String){
        email.value = novoEmail
        erroLogin.value = false
    }

    fun onSenhaChange(novaSenha: String){
        senha.value = novaSenha
        erroLogin.value = false
    }

    fun entrar(onSucesso: () -> Unit){
        viewModelScope.launch {
            val usuarioEncontrado = repository.validarLogin(email.value, senha.value)
            if (usuarioEncontrado != null) {
                erroLogin.value = false
                SessionManager(getApplication()).criarSessao(email.value)
                onSucesso()
            } else {
                erroLogin.value = true
            }
        }
    }
}