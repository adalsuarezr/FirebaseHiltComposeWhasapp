package com.example.firebasehiltcomposewhasapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.*
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
import androidx.compose.runtime.livedata.observeAsState
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
    val messageContent by viewModel.messageContent.observeAsState("")
    Column(Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(text = actualChat?.name.toString()) })

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
            ) {
                actualChat?.let { chat ->
                    items(chat.messageList) { message ->
                        val modifierWithAlignment: Modifier =
                            if (message.diceType) {
                                Modifier
                                    .wrapContentHeight()
                                    .align(Alignment.CenterHorizontally)
                            } else {
                                if (user.hashCode().toString() == message.participant.hashCode().toString()) {
                                    Modifier
                                        .wrapContentHeight()
                                        .align(Alignment.Start)
                                } else {
                                    Modifier
                                        .wrapContentHeight()
                                        .align(Alignment.End)
                                }
                            }
                        Log.d("message", user.hashCode().toString() + " " + message.participant.hashCode().toString())
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Column(Modifier.fillMaxSize().align(Alignment.TopEnd)) {
                                Text(message.participant,modifierWithAlignment)
                                Text(message.content,modifierWithAlignment)
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
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .height(66.dp)
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