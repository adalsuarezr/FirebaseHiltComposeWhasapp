package com.example.firebasehiltcomposewhasapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.firebasehiltcomposewhasapp.data.dto.ChatDTO
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MyHomeViewModel

@Composable
fun ChatScreen(navController: NavHostController, viewModel: MyHomeViewModel) {
    val actualChat by viewModel.actualChat.collectAsState()

    Column(Modifier.fillMaxSize()){
        //LazyColumn(content = )

    }

}