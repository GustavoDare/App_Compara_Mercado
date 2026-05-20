package com.example.comparamercado.data.network

import com.google.gson.annotations.SerializedName

data class ProdutoResponse(
    @SerializedName("products") val produtos: List<ApiProduto>
)

data class ApiProduto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val titulo: String,
    @SerializedName("price") val preco: Double,
    @SerializedName("category") val categoria: String
)
