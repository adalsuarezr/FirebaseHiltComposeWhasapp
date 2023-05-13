package com.example.firebasehiltcomposewhasapp.domain.Repository

import com.example.firebasehiltcomposewhasapp.data.dto.ChatDTO


interface FirebaseRepository {
    fun createUserEmailPassword(email: String, password: String, callback: (Boolean) -> Unit)
    fun loginUserEmailPassword(email: String, password: String, callback: (Boolean) -> Unit)


    fun createUserRegister(email:String)
    fun deleteUserRegister()

    fun getChats(): List<ChatDTO>

    fun createChat()
    fun deleteChat()
    fun updateChatNewMessage()
    fun updateChatNewParticipant()
    fun updateChatRemoveParticipant()


}