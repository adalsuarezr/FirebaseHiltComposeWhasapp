package com.example.firebasehiltcomposewhasapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firebasehiltcomposewhasapp.navigation.AppScreens
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyEmailTextField
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyPasswordTextField
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyRepeatPasswordTextField
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyResetPasswordButton
import com.example.firebasehiltcomposewhasapp.presentation.composables.MySignUpButton
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyTextLink
import com.example.firebasehiltcomposewhasapp.viewmodels.MyViewModel

@Composable
fun ResetPasswordScreen(navController: NavHostController, viewModel: MyViewModel){
    val password: String by viewModel.password.observeAsState(initial = "")
    val repeatPassword: String by viewModel.repeatPassword.observeAsState(initial = "")
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "Reset your password",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top = 48.dp)
        )
        MyPasswordTextField(
            password,
            "Password",
            "Type your password",
            KeyboardType.Password,
            viewModel,
            focusManager
        ) { viewModel.onPasswordChanged(it) }
        MyRepeatPasswordTextField(
            repeatPassword,
            "Repeat password",
            "Type your password",
            KeyboardType.Password,
            viewModel,
            focusManager
        ) { viewModel.onRepeatPasswordChanged(it) }
        MyResetPasswordButton("Reset password", viewModel, navController)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            MyTextLink("Login", navController, AppScreens.LogInScreen)
        }
    }
}