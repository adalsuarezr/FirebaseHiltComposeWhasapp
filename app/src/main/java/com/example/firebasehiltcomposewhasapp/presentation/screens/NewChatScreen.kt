package com.example.firebasehiltcomposewhasapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firebasehiltcomposewhasapp.data.dto.ChatDTO
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyChatNameTextField
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyCreateChatButton
import com.example.firebasehiltcomposewhasapp.presentation.composables.MyParticipantTextField
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MyHomeViewModel

@Composable
fun NewChatScreen(navController: NavHostController, viewModel: MyHomeViewModel) {
    val name: String by viewModel.chatName.observeAsState(initial = "")
    val participant: String by viewModel.participant.observeAsState(initial = "")
    val participantList: MutableList<String> by viewModel.participantList.observeAsState(initial= mutableListOf())
    val focusManager = LocalFocusManager.current
    val enableAddParticipant:Boolean by viewModel.addParticipantButtonEnabled.observeAsState(initial = false)
    var context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "New chat",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top = 48.dp)
        )
        MyChatNameTextField(
            name,
            "Chat name",
            "Name your chat",
            viewModel,
            focusManager,
            onTextChanged = {viewModel.onChatNameChanged(it)}
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            MyParticipantTextField(
                participant,
                "Participant",
                "Add participant",
                viewModel,
                focusManager,
                onTextChanged = {viewModel.onParticipantChanged(it)},
                Modifier.weight(4f)
            )
            OutlinedButton(
                onClick = { viewModel.onAddParticipantClicked() },
                border = null,
                contentPadding= PaddingValues(0.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                modifier = Modifier
                    .padding(16.dp),
                enabled = viewModel.enableAddParticipantButton(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            ) {
                Icon(imageVector = Icons.Default.Add, "", tint = Color.White, modifier = Modifier)
            }
        }
        MyCreateChatButton("Create Chat", viewModel, navController )



        /*MyPasswordTextField(
            password,
            "Password",
            "Type your password",
            KeyboardType.Password,
            viewModel,
            focusManager,
            onTextChanged = { viewModel.onPasswordChanged(it) }
        )
        MyLogInButton("Login", viewModel, context, navController)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            MyTextLink("Forgot password?", navController, AppScreens.ForgottenPasswordScreen)
            MyTextLink("Sign Up", navController, AppScreens.SignUpScreen)
        }*/
    }
}