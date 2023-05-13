package com.example.firebasehiltcomposewhasapp.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firebasehiltcomposewhasapp.data.dto.ChatDTO
import com.example.firebasehiltcomposewhasapp.navigation.AppScreens
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MyHomeViewModel

@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: MyHomeViewModel) {

    val chatList: MutableList<ChatDTO> by homeViewModel.homeChatList.observeAsState(initial = mutableListOf())
    Box(
        Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.fillMaxSize(),Arrangement.Top){
            Row(
                Modifier
                    .height(40.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .border(BorderStroke(2.dp, MaterialTheme.colorScheme.inversePrimary))
            ) {
                Text("HomeScreen", Modifier.padding(8.dp), color = MaterialTheme.colorScheme.primary)
            }

            if(chatList.isEmpty()){CircularProgressIndicator(
                    Modifier
                        .fillMaxWidth()
                        .padding(64.dp), MaterialTheme.colorScheme.primary)}
            else{
                    LazyColumn {
                        items(chatList){chat->
                            Text(text = chat.name)
                            Text(text = chat.emailList.toString().removeSurrounding("[","]"))
                        }
                    }
                }
        }
        OutlinedButton(
            onClick = {  navController.navigate(AppScreens.NewChatScreen.route) },
            border = null,
            shape= CircleShape,
            contentPadding=PaddingValues(0.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp),
            modifier = Modifier
                .padding(16.dp)
                .size(48.dp)
                .align(Alignment.BottomEnd),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary,
            )
        ) {
            Icon(imageVector = Icons.Default.Add, "", tint = Color.White, modifier = Modifier.size(40.dp))
        }
    }

}