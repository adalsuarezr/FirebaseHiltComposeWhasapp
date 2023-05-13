package com.example.firebasehiltcomposewhasapp.domain

import java.io.Serializable

data class Message(val participant: String?=null, val content: String?=null, val diceType: Boolean?=null): Serializable
