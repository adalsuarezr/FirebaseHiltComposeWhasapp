package com.example.firebasehiltcomposewhasapp.presentation.composables

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firebasehiltcomposewhasapp.navigation.AppScreens
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MyHomeViewModel
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MySignInViewModel

@Composable
fun MyLogInButton(placeholder: String, viewModel: MySignInViewModel, context:Context, navController: NavHostController) {
    TextButton(
        onClick = { viewModel.loginAccountEmailPassword(context,navController) },
        Modifier
            .width(180.dp)
            .padding(top = 24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            disabledContainerColor = Color.DarkGray,
        ),
        enabled = viewModel.enableLogin()
    ) {
        Text(text = placeholder, color = Color.LightGray)
    }
}

@Composable
fun MySignUpButton(placeholder: String, viewModel: MySignInViewModel, context:Context, navController: NavHostController) {
    TextButton(
        onClick = { viewModel.createAccountEmailPassword(context,navController) },
        Modifier
            .width(180.dp)
            .padding(top = 24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            disabledContainerColor = Color.DarkGray,
        ),
        enabled = viewModel.enableSignUp()
    ) {
        Text(text = placeholder, color = Color.LightGray)
    }
}

@Composable
fun MySendEmailButton(placeholder: String, viewModel: MySignInViewModel, navController: NavHostController) {
    TextButton(
        onClick = { navController.navigate(AppScreens.ResetPasswordScreen.route) },
        Modifier
            .width(180.dp)
            .padding(top = 24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            disabledContainerColor = Color.DarkGray,
        ),
        enabled = viewModel.enableSendEmail()
    ) {
        Text(text = placeholder, color = Color.LightGray)
    }
}

@Composable
fun MyResetPasswordButton(placeholder: String, viewModel: MySignInViewModel, navController: NavHostController) {
    TextButton(
        onClick = { navController.navigate(AppScreens.LogInScreen.route) },
        Modifier
            .width(180.dp)
            .padding(top = 24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            disabledContainerColor = Color.DarkGray,
        ),
        enabled = viewModel.enableResetPassword()
    ) {
        Text(text = placeholder, color = Color.LightGray)
    }
}

@Composable
fun MyCreateChatButton(placeholder: String, viewModel: MyHomeViewModel, navController: NavHostController) {
    TextButton(
        onClick = { navController.navigate(AppScreens.HomeScreen.route) },
        Modifier
            .width(180.dp)
            .padding(top = 24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            disabledContainerColor = Color.DarkGray,
        ),
        enabled = viewModel.enableCreateChat()
    ) {
        Text(text = placeholder, color = Color.LightGray)
    }
}

