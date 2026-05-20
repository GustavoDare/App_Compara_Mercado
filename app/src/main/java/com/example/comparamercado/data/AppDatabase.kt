package com.example.comparamercado.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.comparamercado.model.Produto
import com.example.comparamercado.model.Usuario
import com.example.comparamercado.ui.features.shopping.ItemCompra

@Database(entities = [Produto::class, ItemCompra::class, Usuario::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
    abstract fun carrinhoDao(): CarrinhoDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "compra_mercado_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}