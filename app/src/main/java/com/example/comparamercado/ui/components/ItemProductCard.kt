package com.example.comparamercado.ui.components

import android.graphics.drawable.Icon
import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comparamercado.R

@Composable
fun ItemProductCard(
    nomeProduto: String,
    local: String,
    preco: String,
    categoria: String,
    onAddClick: () -> Unit,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    exibirControles: Boolean = true
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onAddClick) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = stringResource(id = R.string.prod_card_add),
                    tint = Color(0xFF4CAF50)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = nomeProduto,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )

                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )

                Text(
                    text = categoria,
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(14.dp),
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = stringResource(id = R.string.prod_card_loc),
                        tint = Color.Gray
                    )

                    Spacer(
                        modifier = Modifier
                            .width(4.dp)
                    )

                    Text(
                        text = local,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }

            Text(
                text = preco,
                color = Color(0xFF4CAF50),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            if(exibirControles) {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = stringResource(id = R.string.prod_card_price),
                        tint = Color.Gray
                    )
                }

                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(id = R.string.prod_card_delete),
                        tint = Color.Red
                    )
                }
            }
        }
    }
}