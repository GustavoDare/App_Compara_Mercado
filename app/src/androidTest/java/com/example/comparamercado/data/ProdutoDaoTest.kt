package com.example.comparamercado.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.comparamercado.model.Produto
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProdutoDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: ProdutoDao

    @Before
    fun criarBanco() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.produtoDao()
    }

    @After
    fun fecharBanco() {
        db.close()
    }

    @Test
    fun testarInserirEBuscarProduto() = runBlocking {
        val emailTeste = "teste@ufscar.br"
        val produtoFake = Produto(
            nomeProduto = "Café Especial",
            preco = 18.50,
            donoEmail = emailTeste,
            supermercado = "Mercado Central",
            categoria = "Bebidas"
        )

        dao.insertProduto(produtoFake)

        val listaNoBanco = dao.getMeusProdutos(emailTeste).first()

        assertTrue(
            "A lista não deveria estar vazia",
            listaNoBanco.isNotEmpty())
        assertEquals(
            "O nome do produto salvo está incorreto",
            "Café Especial", listaNoBanco[0].nomeProduto)
        assertEquals(18.50, listaNoBanco[0].preco, 0.0)
    }
}