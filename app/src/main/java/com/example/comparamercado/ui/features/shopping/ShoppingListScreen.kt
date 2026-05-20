package com.example.comparamercado.ui.features.shopping

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.comparamercado.R
import com.example.comparamercado.ui.components.ItemShoppingCard
import com.example.comparamercado.ui.theme.ComparaMercadoTheme

@Composable
fun ShoppingListHeader(
    totalItens: Int,
    valorTotal: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF4CAF50),
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.shop_list_screen_title),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(id = R.string.shop_list_screen_total_items, totalItens),
                color = Color.White.copy(alpha = 0.8f)
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = stringResource(id = R.string.shop_list_screen_price),
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp
            )

            Text(
                valorTotal,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DashedLineAddBotton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onClick()
            }
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = stringResource(id = R.string.shop_list_screen_add_icon),
                tint = Color.Gray
            )

            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )

            Text(
                text = stringResource(id = R.string.shop_list_screen_add_item),
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel = viewModel(),
    onNavigateToSearch: () -> Unit
) {

    val lista by viewModel.listaItens.collectAsState(initial = emptyList())
    val valorTotalEstimado = lista.sumOf { it.preco }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ShoppingListHeader(
            totalItens = lista.size,
            valorTotal = "R$ ${String.format("%.2f", valorTotalEstimado)}"
        )

        if (lista.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                androidx.compose.material3.TextButton(
                    onClick = { viewModel.limparCarrinho() }
                ) {
                    Text(
                        text = stringResource(id = R.string.shop_list_screen_clean_list),
                        color = Color.Red
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(lista) { produto ->
                ItemShoppingCard(
                    nomeProduto = produto.nomeProduto,
                    preco = "R$ ${String.format("%.2f", produto.preco)}",
                    isChecked = produto.isMarcado,
                    onCheckedChange = {
                        viewModel.alterarMarcacao(produto)
                    },
                    onDeleteClick = {
                        viewModel.removerItem(produto)
                    }
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
            }

            item {
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                DashedLineAddBotton (
                    onClick = {
                        onNavigateToSearch()
                    }
                )
            }
        }
    }
}
