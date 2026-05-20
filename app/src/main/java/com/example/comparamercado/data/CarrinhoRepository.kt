package com.example.comparamercado.data

import com.example.comparamercado.ui.features.shopping.ItemCompra
import kotlinx.coroutines.flow.Flow

class CarrinhoRepository(private val carrinhoDao: CarrinhoDao) {

    fun getCarrinho(email: String): Flow<List<ItemCompra>> = carrinhoDao.getCarrinhoUsuario(email)

    suspend fun insert(item: ItemCompra) {
        carrinhoDao.insertItem(item)
    }

    suspend fun update(item: ItemCompra) {
        carrinhoDao.updateItem(item)
    }

    suspend fun delete(item: ItemCompra) {
        carrinhoDao.deleteItem(item)
    }

    suspend fun limparCarrinho(email: String) {
        carrinhoDao.limparCarrinhoUsuario(email)
    }
}