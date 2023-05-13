package com.example.firebasehiltcomposewhasapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firebasehiltcomposewhasapp.navigation.AppScreens
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyTextLink
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MySignInViewModel

@Composable
fun EmailVerificationScreen(navController: NavHostController, viewModel: MySignInViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "Please, check your email",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top = 48.dp)
        )
        Text(
            "To verify your account follow the link we have sent you",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 48.dp)
        )
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            MyTextLink("Go back to Login", navController, AppScreens.LogInScreen)
        }
    }
}