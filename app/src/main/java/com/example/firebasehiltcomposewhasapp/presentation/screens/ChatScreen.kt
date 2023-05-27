package com.example.firebasehiltcomposewhasapp.presentation.screens

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyMessageTextField
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MyHomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavHostController, viewModel: MyHomeViewModel) {
    val actualChat by viewModel.actualChat.collectAsState()
    val user = viewModel.userId
    val focusManager = LocalFocusManager.current
    var messageContent = ""
    Column(Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(text = actualChat?.name.toString() ?: "No name") })

        Column(Modifier.weight(1f)) {
            LazyColumn(Modifier.weight(1f), contentPadding = PaddingValues(vertical = 8.dp)) {
                actualChat?.let { chat ->
                    items(chat.messageList) { message ->
                        val modifierWithAlignment = when {
                            message.diceType -> Modifier.align(Alignment.CenterHorizontally)
                            user == message.participant -> Modifier.align(Alignment.Start)
                            else -> Modifier.align(Alignment.End)
                        }
                        Box(modifier = modifierWithAlignment.fillMaxWidth()) {
                            Column {
                                Text(message.participant)
                                Text(message.content)
                            }
                        }
                    }
                }
            }
        }

        Row(
            Modifier
                .height(70.dp)
                .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyMessageTextField(
                message = messageContent,
                placeholder = "Send Message",
                viewModel = viewModel,
                focusManager = focusManager,
                onTextChanged = { viewModel.onMessageChanged(it) },
                modifier = Modifier.weight(1f).fillMaxWidth().height(66.dp)
            )
            IconButton(
                onClick = { viewModel.onSendMessageClicked() },
                modifier = Modifier.size(66.dp)
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
            }
        }
    }
}
