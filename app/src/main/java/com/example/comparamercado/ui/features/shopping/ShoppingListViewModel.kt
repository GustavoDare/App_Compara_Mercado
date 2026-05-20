package com.example.comparamercado.ui.features.shopping

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.comparamercado.data.AppDatabase
import com.example.comparamercado.data.CarrinhoRepository
import com.example.comparamercado.data.SessionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ShoppingListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CarrinhoRepository
    val listaItens: Flow<List<ItemCompra>>
    private val emailUsuarioAtual = SessionManager(application).getEmailUtilizador() ?: ""

    init {
        val dao = AppDatabase.getDatabase(application).carrinhoDao()
        repository = CarrinhoRepository(dao)
        listaItens = repository.getCarrinho(emailUsuarioAtual)
    }

    fun adicionarItem(novoItem: ItemCompra) {
        viewModelScope.launch {
            repository.insert(novoItem.copy(donoEmail = emailUsuarioAtual))
        }
    }

    fun alterarMarcacao(item: ItemCompra) {
        viewModelScope.launch {
            val itemAtualizado = item.copy(isMarcado = !item.isMarcado)
            repository.update(itemAtualizado)
        }
    }

    fun removerItem(item: ItemCompra) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    fun limparCarrinho() {
        viewModelScope.launch {
            repository.limparCarrinho(emailUsuarioAtual)
        }
    }
}