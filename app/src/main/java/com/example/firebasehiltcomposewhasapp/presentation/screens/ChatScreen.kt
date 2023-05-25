package com.example.firebasehiltcomposewhasapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firebasehiltcomposewhasapp.data.dto.ChatDTO
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MyHomeViewModel

@Composable
fun ChatScreen(navController: NavHostController, viewModel: MyHomeViewModel) {
    val actualChat by viewModel.actualChat.collectAsState()

    Column(Modifier.fillMaxSize(), Arrangement.SpaceBetween){
        LazyColumn(Modifier.padding(4.dp)){
            actualChat?.let {
                items(it.messageList){ message->
                    Text(message.participant)
                    Text(message.content)

                    //message.diceType

                }
            }
        }
        Row(){}
    }

}