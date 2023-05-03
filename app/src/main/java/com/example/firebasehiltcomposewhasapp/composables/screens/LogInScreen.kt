package com.example.firebasehiltcomposewhasapp.composables.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firebasehiltcomposewhasapp.navigation.AppScreens
import com.example.firebasehiltcomposewhasapp.viewmodels.MyViewModel

@Composable
fun LogInScreen(navController: NavHostController, viewModel: MyViewModel) {

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")

    Column(
        Modifier.fillMaxSize()
            .padding(horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "Login",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top = 48.dp)
        )
        MyTextField(
            email,
            "Email",
            "Type your email",
            KeyboardType.Email,
            Icons.Filled.Email,
            Icons.Filled.Email
        ) { viewModel.onEmailChanged(it) }
        MyTextField(
            password,
            "Password",
            "Type your password",
            KeyboardType.Password,
            Icons.Filled.Visibility,
            Icons.Filled.VisibilityOff
        ) { viewModel.onPasswordChanged(it) }
        MyLogInButton("Login", viewModel)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            MyTextLink("Forgot password?", navController, AppScreens.ForgottenPasswordScreen)
            MyTextLink("Sign Up", navController, AppScreens.SignUpScreen)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    string: String,
    label: String,
    placeholder: String,
    keyboard: KeyboardType,
    icon1: ImageVector,
    icon2: ImageVector,
    onTextChanged: (String) -> Unit
) {
    val trailingIconVisibility = remember { mutableStateOf(true) }
    Column(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = string,
            onValueChange = { onTextChanged(it) },
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .background(Color.Transparent),
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            maxLines = 1,
            shape = RoundedCornerShape(24.dp),
            leadingIcon = {
                if (trailingIconVisibility.value) {
                    Icon(
                        icon1,
                        null,
                        Modifier.clickable {
                            trailingIconVisibility.value = !trailingIconVisibility.value
                        },
                        tint = Color.Gray
                    )
                } else {
                    Icon(
                        icon2,
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
            keyboardOptions = KeyboardOptions(keyboardType = keyboard),
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyTextLink(placeholder: String, navController: NavHostController, screen: AppScreens) {
    Text(
        placeholder,
        Modifier
            .padding(top = 24.dp)
            .clickable { navController.navigate(screen.route) },
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun MyLogInButton(placeholder: String, viewModel: MyViewModel) {
    TextButton(
        onClick = {},
        Modifier
            .width(180.dp)
            .padding(top = 24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )
    ) {
        Text(text = placeholder, color = Color.Gray)
    }
}