package com.example.firebasehiltcomposewhasapp.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firebasehiltcomposewhasapp.data.dto.ChatDTO
import com.example.firebasehiltcomposewhasapp.navigation.AppScreens
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MyHomeViewModel

@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: MyHomeViewModel) {
    val chatList: MutableList<ChatDTO> by homeViewModel.homeChatList.observeAsState(initial = mutableListOf())

    Box(
        Modifier.fillMaxSize()
    ) {
        Column(Modifier.fillMaxSize(), Arrangement.Top) {
            Row(
                Modifier
                    .height(40.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .border(BorderStroke(2.dp, MaterialTheme.colorScheme.inversePrimary))
            ) {
                Text(
                    "HomeScreen",
                    Modifier.padding(8.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (chatList.isEmpty()) {
                CircularProgressIndicator(
                    Modifier
                        .fillMaxWidth()
                        .padding(64.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                LazyColumn(Modifier.padding(4.dp)) {
                    items(chatList) { chat ->
                        Button(
                            onClick = {
                                homeViewModel.onChatItemClicked(chat.id)
                                navController.navigate(AppScreens.ChatScreen.route)
                            },
                            Modifier.padding(2.dp).fillMaxWidth(),
                            contentPadding = PaddingValues(0.dp),
                            shape = MaterialTheme.shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MaterialTheme.colorScheme.onSurface
                            )
                        ) {
                            Column(Modifier.clickable {
                                homeViewModel.onChatItemClicked(chat.id)
                                navController.navigate(AppScreens.ChatScreen.route)
                            }) {
                                Text(
                                    text = chat.name,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = chat.emailList.toString().removeSurrounding("[", "]"),
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }

        IconButton(

            onClick = { navController.navigate(AppScreens.NewChatScreen.route) },
            modifier = Modifier
                .padding(16.dp)
                .size(48.dp)
                .align(Alignment.BottomEnd)
                .clip(CircleShape),
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Go new chat screen",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}