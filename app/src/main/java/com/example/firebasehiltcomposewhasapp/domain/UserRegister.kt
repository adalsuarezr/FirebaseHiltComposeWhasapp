package com.example.firebasehiltcomposewhasapp.domain

data class UserRegister (var userId:String?=null, var chatsListIds: MutableList<String> = mutableListOf()){
}