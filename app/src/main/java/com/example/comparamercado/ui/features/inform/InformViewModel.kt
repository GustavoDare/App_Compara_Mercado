package com.example.comparamercado.ui.features.inform

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.comparamercado.data.AppDatabase
import com.example.comparamercado.data.ProductRepository
import com.example.comparamercado.data.SessionManager
import com.example.comparamercado.model.Produto
import kotlinx.coroutines.launch

class InformViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository
    private val emailUsuarioAtual = SessionManager(application).getEmailUtilizador() ?: ""

    init {
        val dao = AppDatabase.getDatabase(application).produtoDao()
        repository = ProductRepository(dao)
    }

    fun salvarProduto(nome: String, mercado: String, categoria: String, preco: String) {
        val precoDouble = preco.replace(",", ".").toDoubleOrNull() ?: 0.0

        val novoProduto = Produto(
            nomeProduto = nome,
            supermercado = mercado,
            categoria = categoria,
            preco = precoDouble,
            donoEmail = emailUsuarioAtual
        )

        viewModelScope.launch {
            repository.insert(novoProduto)
        }
    }
}