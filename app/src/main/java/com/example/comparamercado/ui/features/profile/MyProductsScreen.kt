package com.example.comparamercado.ui.features.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.comparamercado.R
import com.example.comparamercado.model.Produto
import com.example.comparamercado.ui.components.ItemProductCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProductScreeen(
    viewModel: MyProductsViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val listaProdutos by viewModel.meusProdutos.collectAsState(initial = emptyList())

    var produtoParaEditar by remember { mutableStateOf<Produto?>(null) }
    var novoPrecoSugerido by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.prod_screen_title),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.prod_screen_back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4CAF50),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                text = stringResource(id = R.string.prod_screen_history),
                fontSize = 14.sp,
                color = Color.Gray
            )

            if(listaProdutos.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.prod_screen_history_empty),
                        color = Color.LightGray,
                        fontWeight = FontWeight.Medium
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listaProdutos) { produto ->
                        ItemProductCard(
                            nomeProduto = produto.nomeProduto,
                            local = produto.supermercado.ifEmpty {
                                stringResource(id = R.string.prod_screen_card_supermarket)
                            },
                            preco = "R$ ${String.format("%.2f", produto.preco)}",
                            categoria = produto.categoria.ifEmpty {
                                stringResource(id = R.string.prod_screen_card_category)
                            },
                            onAddClick = {
                                viewModel.adicionarAoCarrinho(produto)
                            },
                            onEditClick = {
                                produtoParaEditar = produto
                                novoPrecoSugerido = produto.preco.toString()
                            },
                            onDeleteClick = {
                                viewModel.deletarProduto(produto)
                            }
                        )
                    }
                }
            }

            produtoParaEditar?.let { produto ->
                AlertDialog(
                    onDismissRequest = { produtoParaEditar = null },
                    title = {
                        Text(
                            text = stringResource(id = R.string.alert_price_text)
                        )
                    },
                    text = {
                        Column {
                            Text(
                                modifier = Modifier
                                    .padding(bottom = 8.dp),
                                text = stringResource(id = R.string.alert_price_product, produto.nomeProduto),
                                fontWeight = FontWeight.Bold
                            )
                            OutlinedTextField(
                                value = novoPrecoSugerido,
                                onValueChange = { novoPrecoSugerido = it },
                                label = {
                                    Text(
                                        text = stringResource(id = R.string.alert_price_new_price)
                                    )
                                },
                                singleLine = true
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModel.atualizarPrecoProduto(produto, novoPrecoSugerido)
                                produtoParaEditar = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                        ) {
                            Text(
                                text = stringResource(id = R.string.alert_price_save)
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { produtoParaEditar = null }
                        ) {
                            Text(
                                text = stringResource(id = R.string.alert_price_cancel),
                                color = Color.Gray)
                        }
                    }
                )
            }
        }
    }
}