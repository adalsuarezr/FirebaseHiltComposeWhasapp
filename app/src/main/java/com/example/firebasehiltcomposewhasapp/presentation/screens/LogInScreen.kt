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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firebasehiltcomposewhasapp.navigation.AppScreens
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyEmailTextField
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyLogInButton
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyPasswordTextField
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyTextLink
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MySignInViewModel

@Composable
fun LogInScreen(navController: NavHostController, viewModel: MySignInViewModel) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val focusManager = LocalFocusManager.current
    var context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "Login",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top = 48.dp)
        )
        MyEmailTextField(
            email,
            "Email",
            "Type your email",
            KeyboardType.Email,
            viewModel,
            focusManager,
            onTextChanged = {viewModel.onEmailChanged(it)}
        )
        MyPasswordTextField(
            password,
            "Password",
            "Type your password",
            KeyboardType.Password,
            viewModel,
            focusManager,
            onTextChanged = { viewModel.onPasswordChanged(it) }
        )
        MyLogInButton("Login", viewModel, context, navController)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            MyTextLink("Forgot password?", navController, AppScreens.ForgottenPasswordScreen)
            MyTextLink("Sign Up", navController, AppScreens.SignUpScreen)
        }
    }
}