package com.example.firebasehiltcomposewhasapp.navigation

sealed class AppScreens(val route:String) {
    object SplashScreen:AppScreens("splash_Screen")
    object LogInScreen:AppScreens("log_in_screen")
    object SignUpScreen:AppScreens("sign_up_screen")
    object ForgottenPasswordScreen:AppScreens("forgotten_password_screen")
    object EmailVerificationScreen:AppScreens("email_verification_screen")
    object ResetPasswordScreen:AppScreens("reset_password_screen")
    object HomeScreen:AppScreens("home_screen")
    object ChatScreen:AppScreens("chat_Screen")
}