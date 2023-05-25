package com.example.firebasehiltcomposewhasapp.domain

import java.io.Serializable

data class Message(val participant: String, val content: String, val diceType: Boolean): Serializable
