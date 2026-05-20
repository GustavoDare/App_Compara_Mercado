package com.example.comparamercado.ui.features.inform

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

@Composable
fun InformScreen(
    informViewModel: InformViewModel = viewModel()
) {
    var nomeProduto by remember { mutableStateOf("") }
    var supermercado by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF4CAF50),
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
                .padding(24.dp),
        ) {
            Text(
                text = stringResource(id = R.string.inf_screen_title),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(id = R.string.inf_screen_help),
                color = Color.LightGray,
                fontSize = 16.sp
            )
        }

        Column() {
            Text(
                text = stringResource(id = R.string.inf_screen_product),
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f),
                value = nomeProduto,
                onValueChange = { novoTextoProduto ->
                    nomeProduto = novoTextoProduto
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.inf_screen_product_field),
                        color = Color.Gray
                    )
                },
                shape = RoundedCornerShape(10.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = stringResource(id = R.string.inf_screen_shop_icon),
                        tint = Color.Gray
                    )
                }
            )
        }

        Column() {
            Text(
                text = stringResource(id = R.string.inf_screen_supermarket),
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f),
                value = supermercado,
                onValueChange = { novoTextoSupermercado ->
                    supermercado = novoTextoSupermercado
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.inf_screen_supermarket_name),
                        color = Color.Gray
                    )
                },
                shape = RoundedCornerShape(10.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = stringResource(id = R.string.inf_screen_star_icon),
                        tint = Color.Gray
                    )
                }
            )
        }

        Column() {
            Text(
                text = stringResource(id = R.string.inf_screen_category),
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(fraction = (0.8f)),
                value = categoria,
                onValueChange = { novoTextoCategoria ->
                    categoria = novoTextoCategoria
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.inf_screen_category_field),
                        color = Color.Gray
                    )
                },
                shape = RoundedCornerShape(10.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.inf_screen_add_icon),
                        tint = Color.Gray
                    )
                }
            )
        }

        Column() {
            Text(
                text = stringResource(id = R.string.inf_screen_price),
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f),
                value = preco,
                onValueChange = { novoTextoPreco ->
                    preco = novoTextoPreco
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.inf_screen_price_field),
                        color = Color.Gray
                    )
                },
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Button(
            onClick = {
                if (nomeProduto.isNotBlank() && preco.isNotBlank()) {
                    informViewModel.salvarProduto(
                        nome = nomeProduto,
                        mercado = supermercado,
                        categoria = categoria,
                        preco = preco
                    )
                }

                nomeProduto = "";
                supermercado = "";
                preco = "";
                categoria = ""
            },
            modifier = Modifier
                .fillMaxWidth(fraction = 0.8f)
                .height(46.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.inf_screen_save_product),
                fontSize = 18.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun InformScreenPreview() {
    InformScreen()
}