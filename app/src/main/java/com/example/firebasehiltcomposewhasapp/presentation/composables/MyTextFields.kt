package com.example.firebasehiltcomposewhasapp.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MarkEmailUnread
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MyHomeViewModel
import com.example.firebasehiltcomposewhasapp.presentation.viewmodels.MySignInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyEmailTextField(
    email: String,
    label: String,
    placeholder: String,
    keyboard: KeyboardType,
    viewModel: MySignInViewModel,
    focusManager:FocusManager,
    onTextChanged: (String) -> Unit
) {
    val trailingIconVisibility = rememberSaveable { mutableStateOf(true) }
    Column(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = email,
            onValueChange = { onTextChanged(it) },
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .background(Color.Transparent),
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            maxLines = 1,
            isError = !viewModel.emailValidator() && email.isNotBlank(),
            supportingText = {
                if (!viewModel.emailValidator() && email.isNotBlank()) {
                    Text(viewModel.emailErrorMessage(email), Modifier.fillMaxWidth(), Color.Red)
                } else {
                    ""
                }
            },
            shape = RoundedCornerShape(24.dp),
            leadingIcon = {
                if (trailingIconVisibility.value) {
                    Icon(
                        Icons.Filled.Email,
                        null,
                        Modifier.clickable {
                            trailingIconVisibility.value = !trailingIconVisibility.value
                        },
                        tint = Color.Gray
                    )
                } else {
                    Icon(
                        Icons.Filled.MarkEmailUnread,
                        null,
                        Modifier.clickable {
                            trailingIconVisibility.value = !trailingIconVisibility.value
                        },
                        tint = Color.Gray
                    )
                }
            },
            visualTransformation = if (!trailingIconVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboard, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Down)}),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                placeholderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPasswordTextField(
    password: String,
    label: String,
    placeholder: String,
    keyboard: KeyboardType,
    viewModel: MySignInViewModel,
    focusManager:FocusManager,
    onTextChanged: (String) -> Unit
) {
    val trailingIconVisibility = rememberSaveable { mutableStateOf(true) }

    Column(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = password,
            onValueChange = { onTextChanged(it) },
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .background(Color.Transparent),
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            maxLines = 1,
            isError = (!viewModel.passwordValidator() && password.isNotBlank()),
            supportingText = {
                if (!viewModel.passwordValidator() && password.isNotBlank()) {
                    Text(
                        viewModel.passwordErrorMessage(password),
                        Modifier.fillMaxWidth(),
                        Color.Red
                    )
                } else {
                    ""
                }
            },
            shape = RoundedCornerShape(24.dp),
            leadingIcon = {
                if (trailingIconVisibility.value) {
                    Icon(
                        Icons.Filled.Visibility,
                        null,
                        Modifier.clickable {
                            trailingIconVisibility.value = !trailingIconVisibility.value
                        },
                        tint = Color.Gray
                    )
                } else {
                    Icon(
                        Icons.Filled.VisibilityOff,
                        null,
                        Modifier.clickable {
                            trailingIconVisibility.value = !trailingIconVisibility.value
                        },
                        tint = Color.Gray
                    )
                }
            },
            visualTransformation = if (!trailingIconVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboard, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Down)}),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                placeholderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Black,
                unfocusedIndicatorColor = Color.Black
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRepeatPasswordTextField(
    repeatPassword: String,
    label: String,
    placeholder: String,
    keyboard: KeyboardType,
    viewModel: MySignInViewModel,
    focusManager:FocusManager,
    onTextChanged: (String) -> Unit
) {
    val trailingIconVisibility = rememberSaveable { mutableStateOf(true) }

    Column(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = repeatPassword,
            onValueChange = { onTextChanged(it) },
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .background(Color.Transparent),
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            maxLines = 1,
            isError = (!viewModel.repeatPasswordValidator() && repeatPassword.isNotBlank()),
            supportingText = {
                if (!viewModel.repeatPasswordValidator() && repeatPassword.isNotBlank()) {
                    Text(
                        viewModel.repeatPasswordErrorMessage(repeatPassword),
                        Modifier.fillMaxWidth(),
                        Color.Red
                    )
                } else {
                    ""
                }
            },
            shape = RoundedCornerShape(24.dp),
            leadingIcon = {
                if (trailingIconVisibility.value) {
                    Icon(
                        Icons.Filled.Visibility,
                        null,
                        Modifier.clickable {
                            trailingIconVisibility.value = !trailingIconVisibility.value
                        },
                        tint = Color.Gray
                    )
                } else {
                    Icon(
                        Icons.Filled.VisibilityOff,
                        null,
                        Modifier.clickable {
                            trailingIconVisibility.value = !trailingIconVisibility.value
                        },
                        tint = Color.Gray
                    )
                }
            },
            visualTransformation = if (!trailingIconVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboard, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Down)}),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                placeholderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Black,
                unfocusedIndicatorColor = Color.Black
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyChatNameTextField(
    name: String,
    label: String,
    placeholder: String,
    viewModel: MyHomeViewModel,
    focusManager:FocusManager,
    onTextChanged: (String) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = name,
            onValueChange = { onTextChanged(it) },
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .background(Color.Transparent),
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            maxLines = 1,
            isError = !viewModel.chatNameValidator() && name.isNotBlank(),
            supportingText = {
                if (!viewModel.chatNameValidator() && name.isNotBlank()) {
                    Text(viewModel.chatNameErrorMessage(name), Modifier.fillMaxWidth(), Color.Red)
                } else {
                    ""
                }
            },
            shape = RoundedCornerShape(24.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Down)}),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                placeholderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyParticipantTextField(
    participant: String,
    label: String,
    placeholder: String,
    viewModel: MyHomeViewModel,
    focusManager:FocusManager,
    onTextChanged: (String) -> Unit,
    modifier:Modifier
) {
    Column(modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = participant,
            onValueChange = { onTextChanged(it) },
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .background(Color.Transparent),
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            maxLines = 1,
            isError = !viewModel.participantValidator() && participant.isNotBlank(),
            supportingText = {
                if (!viewModel.participantValidator() && participant.isNotBlank()) {
                    Text(viewModel.participantErrorMessage(participant), Modifier.fillMaxWidth(), Color.Red)
                } else {
                    ""
                }
            },
            shape = RoundedCornerShape(24.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Down)}),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                placeholderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}