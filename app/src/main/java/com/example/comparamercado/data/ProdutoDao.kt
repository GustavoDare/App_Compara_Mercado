package com.example.comparamercado.data

import androidx.room.*
import com.example.comparamercado.model.Produto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao {
    @Query("SELECT * FROM produtos ORDER BY nomeProduto ASC")
    fun getAllProdutos(): Flow<List<Produto>>

    @Query("SELECT * FROM produtos WHERE donoEmail = :email")
    fun getMeusProdutos(email: String): Flow<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduto(produto: Produto): Long

    @Delete
    suspend fun deleteProduto(produto: Produto): Int

    @Update
    suspend fun updateProduto(produto: Produto): Int

    @Query("SELECT * FROM produtos WHERE nomeProduto LIKE '%' || :busca || '%'")
    fun searchProdutos(busca: String): Flow<List<Produto>>
}