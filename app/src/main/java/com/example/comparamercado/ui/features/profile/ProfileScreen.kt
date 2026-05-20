package com.example.comparamercado.ui.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.comparamercado.R

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    onLogoutClick: () -> Unit,
    onMyProductsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = Color(0xFF4CAF50),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(60.dp),
                imageVector = Icons.Default.Person,
                contentDescription = stringResource(id = R.string.profile_screen_icon),
                tint = Color.White
            )
        }

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        Text(
            text = stringResource(id = R.string.profile_screen_user),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = viewModel.emailUtilizado.value,
            color = Color.Gray,
            fontSize = 16.sp
        )

        Spacer(
            modifier = Modifier
                .height(32.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ProfileMenuItem(
                icon = Icons.Default.List,
                text = stringResource(id = R.string.profile_screen_products),
                onClick = onMyProductsClick
            )

            ProfileMenuItem(
                icon = Icons.Default.Settings,
                text = stringResource(id = R.string.profile_screen_config),
                onClick = onSettingsClick
            )
        }

        Spacer(
            modifier = Modifier
                .weight(1f)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                viewModel.fecharSessao()
                onLogoutClick()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5252)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = stringResource(id = R.string.profile_screen_out_icon),
                tint = Color.White
            )

            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )

            Text(
                text = stringResource(id = R.string.profile_screen_out_account),
                fontSize = 16.sp
            )
        }

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color(0xFF4CAF50)
        )

        Spacer(
            modifier = Modifier
                .width(16.dp)
        )

        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}
*/