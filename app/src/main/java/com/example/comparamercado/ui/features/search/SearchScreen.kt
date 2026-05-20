package com.example.comparamercado.ui.features.search

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.comparamercado.R
import com.example.comparamercado.model.Produto
import com.example.comparamercado.ui.components.ItemProductCard
import com.example.comparamercado.ui.components.SearchHeader
import com.example.comparamercado.ui.features.shopping.ItemCompra
import com.example.comparamercado.ui.features.shopping.ShoppingListViewModel
import com.example.comparamercado.ui.theme.ComparaMercadoTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider

@Composable
fun SearchScreen(
    viewModel: ShoppingListViewModel,
    searchViewModel: SearchViewModel = viewModel()
) {

    var termoBusca by remember { mutableStateOf("") }

    var produtoParaEditar by remember { mutableStateOf<Produto?>(null) }
    var novoPrecoSugerido by remember { mutableStateOf("") }

    val listaProdutos by searchViewModel.allProdutos.collectAsState(initial = emptyList())

    val listaFiltrada = listaProdutos.filter { produto ->
        produto.nomeProduto.contains(termoBusca, ignoreCase = true) ||
                produto.supermercado.contains(termoBusca, ignoreCase = true) ||
                produto.categoria.contains(termoBusca, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        SearchHeader(
            termoBusca = termoBusca,
            onBuscaChange = { novoTermo -> termoBusca = novoTermo}
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onClick = { searchViewModel.carregarOfertasOnline() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xD2B48C80))
        ) {
            Text(
                text = stringResource(id = R.string.search_screen_load_offer)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            if (searchViewModel.ofertasDaApi.value.isNotEmpty()) {
                item {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        text = "Ofertas da Internet:",
                        fontWeight = FontWeight.Bold
                    )
                }

                items(searchViewModel.ofertasDaApi.value) { produto ->
                    ItemProductCard(
                        nomeProduto = produto.nomeProduto,
                        local = produto.supermercado,
                        preco = "R$ ${String.format("%.2f", produto.preco)}",
                        categoria = produto.categoria,
                        exibirControles = false,
                        onAddClick = {
                            val novoItemCompra = ItemCompra(
                                id = (1..10000).random(),
                                nomeProduto = produto.nomeProduto,
                                preco = produto.preco
                            )
                            viewModel.adicionarItem(novoItemCompra)
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )
                }

                item {
                    Divider(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        color = Color.LightGray
                    )
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    text = stringResource(id = R.string.search_screen_list),
                    fontWeight = FontWeight.Bold,
                )
            }

            if (listaFiltrada.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.search_screen_empty),
                            color = Color.Gray,
                            fontSize = 18.sp
                        )
                    }
                }
            } else {
                items(listaFiltrada) { produtoAtual ->
                    ItemProductCard(
                        nomeProduto = produtoAtual.nomeProduto,
                        local = produtoAtual.supermercado,
                        preco = String.format("R$ %.2f", produtoAtual.preco),
                        categoria = produtoAtual.categoria,
                        onAddClick = {
                            val novoItemCompra = ItemCompra(
                                id = (1..10000).random(),
                                nomeProduto = produtoAtual.nomeProduto,
                                preco = produtoAtual.preco
                            )
                            viewModel.adicionarItem((novoItemCompra))
                        },
                        onEditClick = {
                            produtoParaEditar = produtoAtual
                            novoPrecoSugerido = produtoAtual.preco.toString()
                        },
                        onDeleteClick = {
                            searchViewModel.deletarProduto(produtoAtual)
                        }
                    )
                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                    )
                }
            }
        }
    }

    if(produtoParaEditar != null) {
        AlertDialog(
            onDismissRequest = {
                produtoParaEditar = null
            },
            title = {
                Text(
                    text = stringResource(id = R.string.alert_price_text)
                )
            },
            text = {
                Column {
                    Text(
                        text = stringResource(id = R.string.alert_price_product, produtoParaEditar?.nomeProduto ?: "")
                    )
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )
                    OutlinedTextField(
                        value = novoPrecoSugerido,
                        onValueChange = { novoPrecoSugerido = it },
                        label = {
                            Text(
                                text = stringResource(id = R.string.alert_price_new_price)
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        produtoParaEditar?.let { produto ->
                            searchViewModel.atualizarPrecoProduto(
                                produtoAtual = produto,
                                novoPrecoStr = novoPrecoSugerido
                            )
                        }
                        produtoParaEditar = null
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.alert_price_save)
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        produtoParaEditar = null
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.alert_price_cancel)
                    )
                }
            }
        )
    }
}