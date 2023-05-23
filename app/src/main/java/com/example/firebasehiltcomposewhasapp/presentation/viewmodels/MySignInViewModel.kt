package com.example.firebasehiltcomposewhasapp.presentation.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.firebasehiltcomposewhasapp.domain.repository.FirebaseRepositoryImpl
import com.example.firebasehiltcomposewhasapp.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MySignInViewModel @Inject constructor() : ViewModel() {
    private val repository = FirebaseRepositoryImpl()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword: LiveData<String> = _repeatPassword

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
    }

    fun onRepeatPasswordChanged(repeatPassword: String) {
        _repeatPassword.value = repeatPassword
    }

    fun emailErrorMessage(email: String): String {
        var errorMessage = ""
        if (!email.isNullOrEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) {
            errorMessage = "Not valid Email format"
        }
        return errorMessage
    }

    fun passwordErrorMessage(password: String): String {
        var errorMessage = ""
        if (password.length < 8) {
            errorMessage = "Password too short. ${password.length}/8"
        }
        return errorMessage
    }

    fun repeatPasswordErrorMessage(repeatPassword: String): String {
        var errorMessage = ""
        if (_password.value != repeatPassword) {
            errorMessage = "Passwords doesn't match"
        }
        return errorMessage
    }

    fun emailValidator(): Boolean {
        var validation = false
        if (_email.value.toString().isNotEmpty()) {
            validation =
                android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value.toString()).matches()
        }
        return validation
    }

    fun passwordValidator(): Boolean {
        var validation = false
        if (!_password.value.toString().isNullOrEmpty()) {
            if (_password.value.toString().length >= 8) {
                validation = true
            }
        }
        return validation
    }

    fun repeatPasswordValidator(): Boolean {
        var validation = false
        if (!_repeatPassword.value.toString().isNullOrEmpty()) {
            if (_password.value == _repeatPassword.value) {
                validation = true
            }
        }
        return validation
    }

    fun enableLogin(): Boolean {
        var boolean = false
        if (emailValidator() && passwordValidator()) {
            boolean = true
        }
        return boolean
    }

    fun enableSignUp(): Boolean {
        var boolean = false
        if (emailValidator() && passwordValidator() && repeatPasswordValidator()) {
            boolean = true
        }
        return boolean
    }

    fun enableSendEmail(): Boolean {
        var boolean = false
        if (emailValidator()) {
            boolean = true
        }
        return boolean
    }

    fun enableResetPassword(): Boolean {
        var boolean = false
        if (repeatPasswordValidator() && passwordValidator()) {
            boolean = true
        }
        return boolean
    }

    fun createAccountEmailPassword(context: Context, navController: NavController){
        repository.createUserEmailPassword(_email.value.toString(),_password.value.toString()){success->
            if(success){
                navController.navigate(AppScreens.EmailVerificationScreen.route)
            }else{
                makeToast(context, "Failed to create account", Toast.LENGTH_LONG)
            }

        }
    }

    fun loginAccountEmailPassword(context: Context, navController: NavController){
        repository.loginUserEmailPassword(_email.value.toString(),_password.value.toString()){success->
            if(success){
                navController.navigate(AppScreens.HomeScreen.route)
            }else{
                if(FirebaseAuth.getInstance().currentUser?.isEmailVerified == false){
                    FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                    navController.navigate(AppScreens.EmailVerificationScreen.route)
                }else{
                    makeToast(context, "Failed to login with the information provided", Toast.LENGTH_LONG)
                }
            }
        }
    }
    private fun makeToast(context: Context, message: String, length: Int){
        Toast.makeText(context,message,length).show()
    }
}