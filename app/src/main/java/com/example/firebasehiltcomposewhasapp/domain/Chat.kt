package com.example.firebasehiltcomposewhasapp.domain

import java.io.Serializable

data class Chat(var id:String, var name: String, var emailList: MutableList<String> = mutableListOf(), var messageList: MutableList<Message> = mutableListOf()):
    Serializable {


    override fun toString(): String {
        return "$name\r $emailList"
    }
}