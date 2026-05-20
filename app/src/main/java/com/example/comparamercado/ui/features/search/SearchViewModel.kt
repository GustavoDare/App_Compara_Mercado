package com.example.comparamercado.ui.features.search

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.comparamercado.R
import com.example.comparamercado.data.AppDatabase
import com.example.comparamercado.data.ProductRepository
import com.example.comparamercado.data.SessionManager
import com.example.comparamercado.data.network.RetrofitClient
import com.example.comparamercado.model.Produto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository
    val allProdutos: Flow<List<Produto>>
    var ofertasDaApi = mutableStateOf<List<Produto>>(emptyList())
        private set

    init {
        val dao = AppDatabase.getDatabase(application).produtoDao()
        repository = ProductRepository(dao)
        allProdutos = repository.allProdutos
    }

    fun deletarProduto(produto: Produto) {
        viewModelScope.launch {
            repository.delete(produto)
        }
    }

    fun atualizarPrecoProduto(produtoAtual: Produto, novoPrecoStr: String) {
        val precoDouble = novoPrecoStr.replace(",", ".").toDoubleOrNull()

        if (precoDouble != null && precoDouble > 0) {
            val produtoAtualizado = produtoAtual.copy(preco = precoDouble)

            viewModelScope.launch {
                repository.update(produtoAtualizado)
            }
        }
    }

    fun carregarOfertasOnline() {
        viewModelScope.launch {
            try {
                val resposta = RetrofitClient.api.getOfertasDoDia()

                val listaConvertida = resposta.produtos.map { apiProduto ->
                    Produto(
                        id = 0,
                        nomeProduto = apiProduto.titulo,
                        preco = apiProduto.preco * 0.5,
                        donoEmail = getApplication<Application>().getString(R.string.search_view_model_email),
                        supermercado = getApplication<Application>().getString(R.string.search_view_model_market),
                        categoria = apiProduto.categoria
                    )
                }
                ofertasDaApi.value = listaConvertida
                Log.d(
                    "API_TESTE",
                    getApplication<Application>().getString(R.string.search_view_model_load_sucess)
                )
            } catch (e: Exception) {
                Log.e(
                    "API_TESTE",
                    getApplication<Application>().getString(R.string.search_view_model_load_error)
                )
            }
        }
    }
}