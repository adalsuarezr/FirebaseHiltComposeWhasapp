package com.example.firebasehiltcomposewhasapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebasehiltcomposewhasapp.presentation.screens.ChatScreen
import com.example.firebasehiltcomposewhasapp.presentation.screens.EmailVerificationScreen
import com.example.firebasehiltcomposewhasapp.presentation.screens.ForgottenPasswordScreen
import com.example.firebasehiltcomposewhasapp.presentation.screens.HomeScreen
import com.example.firebasehiltcomposewhasapp.presentation.screens.LogInScreen
import com.example.firebasehiltcomposewhasapp.presentation.screens.ResetPasswordScreen
import com.example.firebasehiltcomposewhasapp.presentation.screens.SignUpScreen
import com.example.firebasehiltcomposewhasapp.presentation.screens.SplashScreen
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
            ForgottenPasswordScreen(navController, viewModel)
        }
        composable(AppScreens.EmailVerificationScreen.route){
            EmailVerificationScreen(navController, viewModel)
        }
        composable(AppScreens.ResetPasswordScreen.route){
            ResetPasswordScreen(navController, viewModel)
        }
        composable(AppScreens.HomeScreen.route) { backStackEntry -> navController.navigate(AppScreens.HomeScreen.route){
            popUpTo(AppScreens.LogInScreen.route){}
        }
            HomeScreen(navController, viewModel)
        }
        composable(AppScreens.ChatScreen.route){
            ChatScreen(navController)
        }
    }
}