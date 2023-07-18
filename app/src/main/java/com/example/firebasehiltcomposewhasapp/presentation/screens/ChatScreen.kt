package com.example.firebasehiltcomposewhasapp.presentation.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
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

    val iconEraseMessageVisibility = false

    val lazyListState = rememberLazyListState()
    LaunchedEffect(actualChat) {
        if (!actualChat?.messageList.isNullOrEmpty()) {
            lazyListState.scrollToItem(actualChat!!.messageList.size - 1)
        }
    }

    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = actualChat?.name.toString()) },
            actions = {
                if (iconEraseMessageVisibility) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete message"
                        )
                    }
                }
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                state = lazyListState,
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
                                Modifier.wrapContentHeight()
                            }
                        val boxAlignment: Alignment =
                            if (message.diceType) {
                                Alignment.TopCenter
                            } else {
                                if (user.hashCode().toString() == message.participant.hashCode()
                                        .toString()
                                ) {
                                    Alignment.TopStart
                                } else {
                                    Alignment.TopEnd
                                }
                            }
                        val cardBackgroundColor: Color =
                            if (message.diceType) {
                                Color(150, 150, 150)
                            } else {
                                if (user.hashCode().toString() == message.participant.hashCode()
                                        .toString()
                                ) {
                                    Color(220, 220, 220)
                                } else {
                                    Color(170, 170, 170)
                                }
                            }
                        val paddingValues =
                            if (message.diceType) {
                                PaddingValues(top = 2.dp, bottom = 2.dp)
                            } else {
                                if (user.hashCode().toString() == message.participant.hashCode()
                                        .toString()
                                ) {
                                    PaddingValues(
                                        start = 0.dp,
                                        end = 30.dp,
                                        top = 2.dp,
                                        bottom = 2.dp
                                    )
                                } else {
                                    PaddingValues(
                                        start = 30.dp,
                                        end = 0.dp,
                                        top = 2.dp,
                                        bottom = 2.dp
                                    )
                                }
                            }

                        Box(
                            modifier = Modifier
                                .padding(paddingValues)
                                .fillMaxWidth(),
                            contentAlignment = boxAlignment
                        ) {
                            Card(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .pointerInput(Unit) {
                                        detectTapGestures(onLongPress = {/*change visibility buttons tollbar*/

                                        })
                                    },
                                colors = CardDefaults.cardColors(
                                    containerColor = cardBackgroundColor
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .padding(8.dp)
                                ) {
                                    Text(message.participant, modifierWithAlignment)
                                    Text(message.content, modifierWithAlignment)
                                }
                            }
                        }
                    }
                }
            }
        }

        Row(
            Modifier
                .padding(2.dp)
                .wrapContentHeight(),
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
            )
            IconButton(
                onClick = { viewModel.onSendMessageClicked() },
                modifier = Modifier.wrapContentSize(),
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send",
                    modifier = Modifier
                        .size(66.dp)
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}
