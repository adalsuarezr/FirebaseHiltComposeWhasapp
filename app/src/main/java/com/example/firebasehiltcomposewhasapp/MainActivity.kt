package com.example.firebasehiltcomposewhasapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.firebasehiltcomposewhasapp.navigation.AppNavigation
import com.example.firebasehiltcomposewhasapp.ui.theme.FirebaseHiltComposeWhasappTheme
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MyHomeViewModel
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MySignInViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val accessViewModel: MySignInViewModel by viewModels()
    private val homeViewModel: MyHomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val analytics = FirebaseAnalytics.getInstance(this)
            val bundle = Bundle()
            bundle.putString("message", "Firebase integration complete")
            analytics.logEvent("InitScreen", bundle)

            FirebaseHiltComposeWhasappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(accessViewModel, homeViewModel)
                }
            }
        }
    }
}

