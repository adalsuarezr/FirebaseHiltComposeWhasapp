package com.example.firebasehiltcomposewhasapp.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firebasehiltcomposewhasapp.navigation.AppScreens

@Composable
fun MyTextLink(placeholder: String, navController: NavHostController, screen: AppScreens) {
    Text(
        placeholder,
        Modifier
            .padding(top = 24.dp)
            .clickable { navController.navigate(screen.route) },
        color = MaterialTheme.colorScheme.primary
    )
}