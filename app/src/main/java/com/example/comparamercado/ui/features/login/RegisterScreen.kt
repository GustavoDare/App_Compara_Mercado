package com.example.comparamercado.ui.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.comparamercado.R

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    onRegisterSuccess: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0x33ACAF50)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.reg_screen_new_account),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            value = viewModel.email.value,
            onValueChange = {
                viewModel.onEmailChange(it)
            },
            label = {
                Text(
                    text = stringResource(id = R.string.reg_screen_email)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            value = viewModel.senha.value,
            onValueChange = {
                viewModel.onSenhaChange(it)
            },
            label = {
                Text(
                    text = stringResource(id = R.string.reg_screen_password)
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            value = viewModel.confirmarSenha.value,
            onValueChange = {
                viewModel.onConfirmarSenhaChange(it)
            },
            label = {
                Text(
                    text = stringResource(id = R.string.reg_screen_password_conf)
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )

        if (viewModel.mensagemErro.value.isNotEmpty()) {
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            Text(
                text = viewModel.mensagemErro.value,
                color = Color.Red,
                fontSize = 14.sp
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = {
                viewModel.cadastrar(onSucesso = onRegisterSuccess)
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.reg_screen_reg),
                fontSize = 18.sp
            )
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        TextButton(
            onClick = onNavigateBack
        ) {
            Text(
                text = stringResource(id = R.string.reg_screen_back),
                color = Color(0xFF4CAF50)
            )
        }
    }
}