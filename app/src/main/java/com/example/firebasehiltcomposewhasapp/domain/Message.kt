package com.example.firebasehiltcomposewhasapp.domain

import java.io.Serializable

data class Message(val content: String, val diceType: Boolean, val participant: String, val id: Long =System.currentTimeMillis()+participant.hashCode()): Serializable
