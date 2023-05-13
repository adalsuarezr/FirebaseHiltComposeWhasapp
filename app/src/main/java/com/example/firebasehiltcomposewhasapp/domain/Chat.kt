package com.example.firebasehiltcomposewhasapp.domain

import java.io.Serializable

data class Chat(var name: String, var emailList: MutableList<String> = mutableListOf(), var messageList: MutableList<Message> = mutableListOf()):
    Serializable {
    var id: String= setOf(emailList).hashCode().toString()+name.hashCode().toString()

    override fun toString(): String {
        return "$name\r $emailList"
    }
}