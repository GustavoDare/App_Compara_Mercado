package com.example.comparamercado.data

import com.example.comparamercado.model.Usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun cadastrar(usuario: Usuario): Long{
        return usuarioDao.cadastrarUsuario(usuario)
    }

    suspend fun validarLogin(email: String, senha: String): Usuario?{
        return usuarioDao.login(email, senha)
    }
}