package com.example.firebasehiltcomposewhasapp.data.dto

class ChatDTO(var id:String, var name: String, var emailList: MutableList<String>, var messageList: MutableList<MessageDTO>) {
    constructor() : this("", "", mutableListOf(), mutableListOf())
}