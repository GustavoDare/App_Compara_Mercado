package com.example.comparamercado.ui.features.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.comparamercado.data.AppDatabase
import com.example.comparamercado.data.CarrinhoRepository
import com.example.comparamercado.data.ProductRepository
import com.example.comparamercado.data.SessionManager
import com.example.comparamercado.model.Produto
import com.example.comparamercado.ui.features.shopping.ItemCompra
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MyProductsViewModel (application: Application) : AndroidViewModel(application) {
    private val productRepository: ProductRepository
    private val carrinhoRepository: CarrinhoRepository
    val meusProdutos: Flow<List<Produto>>
    private val emailAtual = SessionManager(application).getEmailUtilizador() ?: ""

    init {
        val db = AppDatabase.getDatabase(application)
        productRepository = ProductRepository(db.produtoDao())
        carrinhoRepository = CarrinhoRepository(db.carrinhoDao())
        meusProdutos = productRepository.getMeusProdutos(emailAtual)
    }

    fun deletarProduto(produto: Produto) {
        viewModelScope.launch {
            productRepository.delete(produto)
        }
    }

    fun atualizarPrecoProduto(produto: Produto, novoPrecoStr: String) {
        val precoDouble = novoPrecoStr.replace(",", ".").toDoubleOrNull()
        if (precoDouble != null && precoDouble > 0) {
            viewModelScope.launch {
                productRepository.update(produto.copy(preco = precoDouble))
            }
        }
    }

    fun adicionarAoCarrinho(produto: Produto) {
        viewModelScope.launch {
            val novoItem = ItemCompra(
                nomeProduto = produto.nomeProduto,
                preco = produto.preco,
                isMarcado = false,
                donoEmail = emailAtual
            )
            carrinhoRepository.insert(novoItem)
        }
    }
}