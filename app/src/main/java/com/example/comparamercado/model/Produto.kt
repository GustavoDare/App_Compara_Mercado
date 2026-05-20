package com.example.comparamercado.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produtos")
data class Produto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nomeProduto: String,
    val supermercado: String,
    val categoria: String,
    val preco: Double,
    val donoEmail: String = ""
)
