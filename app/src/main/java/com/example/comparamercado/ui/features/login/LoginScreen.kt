package com.example.comparamercado.ui.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.comparamercado.R
import com.example.comparamercado.ui.theme.ComparaMercadoTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0x334CAF50)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(color = Color(0xFF4CAF50), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = stringResource(id = R.string.login_screen_app_icon),
                tint = Color.White
            )
        }

        Text(
            text = stringResource(id = R.string.login_screen_app_name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        
        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            text = stringResource(id = R.string.login_screen_sub_title),
            textAlign = TextAlign.Center,
            color = Color.Gray,
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth((0.8f)),
            value = viewModel.email.value,
            onValueChange = { novoTextoEmail -> viewModel.onEmailChange(novoTextoEmail) },
            label = {
                Text(
                    text = stringResource(id = R.string.login_screen_email)
                ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            value = viewModel.senha.value,
            onValueChange = { novoTextoSenha -> viewModel.onSenhaChange(novoTextoSenha) },
            label = {
                Text(
                    text = stringResource(id = R.string.login_screen_password)
                ) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (viewModel.erroLogin.value) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = stringResource(id = R.string.login_screen_error_fields),
                color = Color.Red,
                fontSize = 14.sp
            )
        }

        Button (
            onClick = {
                viewModel.entrar(
                    onSucesso = {
                        onLoginSuccess()
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text (
                text = stringResource(id = R.string.login_screen_enter),
                fontSize = 18.sp)
        }

        TextButton (
            onClick = {
                onNavigateToRegister()
            }
        ) {
            Text(
                text = stringResource(id = R.string.login_screen_new_account),
                color = Color(0xFF4CAF50))
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    ComparaMercadoTheme() {
        LoginScreen()
    }
}
*/