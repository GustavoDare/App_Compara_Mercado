package com.example.comparamercado.data

import androidx.room.*
import com.example.comparamercado.ui.features.shopping.ItemCompra
import kotlinx.coroutines.flow.Flow

@Dao
interface CarrinhoDao {
    @Query("SELECT * FROM carrinho WHERE donoEmail = :email")
    fun getCarrinhoUsuario(email: String): Flow<List<ItemCompra>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ItemCompra): Long

    @Update
    suspend fun updateItem(item: ItemCompra): Int

    @Delete
    suspend fun deleteItem(item: ItemCompra): Int

    @Query("DELETE FROM carrinho WHERE donoEmail = :email")
    suspend fun limparCarrinhoUsuario(email: String): Int
}