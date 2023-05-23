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
import com.example.firebasehiltcomposewhasapp.presentation.screens.NewChatScreen
import com.example.firebasehiltcomposewhasapp.presentation.screens.ResetPasswordScreen
import com.example.firebasehiltcomposewhasapp.presentation.screens.SignUpScreen
import com.example.firebasehiltcomposewhasapp.presentation.screens.SplashScreen
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MyHomeViewModel
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MySignInViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation(
    accessViewModel: MySignInViewModel,
    homeViewModel: MyHomeViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController, accessViewModel)
        }
        composable(AppScreens.LogInScreen.route){
            LogInScreen(navController, accessViewModel)
        }
        composable(AppScreens.SignUpScreen.route){
            SignUpScreen(navController, accessViewModel)
        }
        composable(AppScreens.ForgottenPasswordScreen.route){
            ForgottenPasswordScreen(navController, accessViewModel)
        }
        composable(AppScreens.EmailVerificationScreen.route){
            EmailVerificationScreen(navController, accessViewModel)
        }
        composable(AppScreens.ResetPasswordScreen.route){
            ResetPasswordScreen(navController, accessViewModel)
        }
        /*composable(AppScreens.HomeScreen.route) { backStackEntry ->
            navController.navigate(AppScreens.HomeScreen.route){
            popUpTo(AppScreens.LogInScreen.route){}
        }
            HomeScreen(navController, viewModel)
        }*/
        composable(AppScreens.HomeScreen.route){
            HomeScreen(navController, homeViewModel)
        }
        composable(AppScreens.NewChatScreen.route){
            NewChatScreen(navController, homeViewModel)
        }
        composable(AppScreens.ChatScreen.route){
            ChatScreen(navController, homeViewModel)
        }
    }
}