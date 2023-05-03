package com.example.firebasehiltcomposewhasapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebasehiltcomposewhasapp.composables.screens.ChatScreen
import com.example.firebasehiltcomposewhasapp.composables.screens.HomeScreen
import com.example.firebasehiltcomposewhasapp.composables.screens.LogInScreen
import com.example.firebasehiltcomposewhasapp.composables.screens.SignUpScreen
import com.example.firebasehiltcomposewhasapp.composables.screens.SplashScreen
import com.example.firebasehiltcomposewhasapp.viewmodels.MyViewModel

@Composable
fun AppNavigation(viewModel: MyViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController, viewModel)
        }
        composable(AppScreens.LogInScreen.route){
            LogInScreen(navController, viewModel)
        }
        composable(AppScreens.SignUpScreen.route){
            SignUpScreen(navController, viewModel)
        }
        composable(AppScreens.ForgottenPasswordScreen.route){
            SignUpScreen(navController, viewModel)
        }
        composable(AppScreens.HomeScreen.route){
            HomeScreen(navController, viewModel)
        }
        composable(AppScreens.ChatScreen.route){
            ChatScreen(navController)
        }
    }
}