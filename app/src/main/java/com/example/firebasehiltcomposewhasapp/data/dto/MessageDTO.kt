package com.example.firebasehiltcomposewhasapp.data.dto

data class MessageDTO (val content: String, val diceType: Boolean, val participant: String, val id:Long) {
    constructor() : this("", false, "", 0L)
}