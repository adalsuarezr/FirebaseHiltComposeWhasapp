package com.example.firebasehiltcomposewhasapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.firebasehiltcomposewhasapp.data.dto.ChatDTO
import com.example.firebasehiltcomposewhasapp.data.dto.MessageDTO


interface FirebaseRepository {
    fun createUserEmailPassword(email: String, password: String, callback: (Boolean) -> Unit)
    fun loginUserEmailPassword(email: String, password: String, callback: (Boolean) -> Unit)

    fun onActiveChatChanged(chatId: String)
    fun getMessages(): LiveData<List<MessageDTO>>
    fun sendMessage(content: String)


    fun createUserRegister(email: String)
    fun deleteUserRegister()

    fun getChats(): List<ChatDTO>
    fun createChat(chat: ChatDTO, callback: (Boolean) -> Unit)
    fun deleteChat()
    fun updateChatNewMessage()
    fun updateChatNewParticipant()
    fun updateChatRemoveParticipant()

}