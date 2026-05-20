package com.example.comparamercado.data

import com.example.comparamercado.model.Produto
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val produtoDao: ProdutoDao) {
    val allProdutos: Flow<List<Produto>> = produtoDao.getAllProdutos()

    suspend fun insert(produto: Produto) {
        produtoDao.insertProduto(produto)
    }

    suspend fun delete(produto: Produto) {
        produtoDao.deleteProduto(produto)
    }

    suspend fun update(produto: Produto) {
        produtoDao.updateProduto(produto)
    }

    fun search(query: String): Flow<List<Produto>> {
        return produtoDao.searchProdutos(query)
    }

    fun getMeusProdutos(email: String): Flow<List<Produto>> {
        return produtoDao.getMeusProdutos(email)
    }
}