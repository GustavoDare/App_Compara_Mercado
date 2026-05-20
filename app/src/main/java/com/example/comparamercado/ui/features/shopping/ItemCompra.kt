package com.example.comparamercado.ui.features.shopping

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carrinho")
data class ItemCompra(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nomeProduto: String,
    val preco: Double,
    var isMarcado: Boolean = false,
    val donoEmail: String = ""
)
