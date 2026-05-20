package com.example.comparamercado.ui.features.login

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.comparamercado.R
import com.example.comparamercado.data.AppDatabase
import com.example.comparamercado.data.UsuarioRepository
import com.example.comparamercado.model.Usuario
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UsuarioRepository

    var email = mutableStateOf("")
        private set
    var senha = mutableStateOf("")
        private set
    var confirmarSenha = mutableStateOf("")
        private set
    var mensagemErro = mutableStateOf("")
        private set

    init {
        val dao = AppDatabase.getDatabase(application).usuarioDao()
        repository = UsuarioRepository(dao)
    }

    fun onEmailChange(novo: String) {
        email.value = novo;
        mensagemErro.value = ""
    }

    fun onSenhaChange(novo: String) {
        senha.value = novo;
        mensagemErro.value = ""
    }

    fun onConfirmarSenhaChange(novo: String) {
        confirmarSenha.value = novo;
        mensagemErro.value = ""
    }

    fun cadastrar(onSucesso: () -> Unit) {
        if (email.value.isBlank() || senha.value.isBlank()) {
            mensagemErro.value = getApplication<Application>().getString(R.string.reg_view_fill_fields)
            return
        }

        if (senha.value != confirmarSenha.value) {
            mensagemErro.value = getApplication<Application>().getString(R.string.reg_view_error_password)
            return
        }

        viewModelScope.launch {
            val novoUsuario = Usuario(email = email.value, senha = senha.value)
            repository.cadastrar(novoUsuario)
            onSucesso()
        }
    }
}