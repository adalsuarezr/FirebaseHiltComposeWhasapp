package com.example.firebasehiltcomposewhasapp.presentation.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebasehiltcomposewhasapp.data.dto.ChatDTO
import com.example.firebasehiltcomposewhasapp.domain.Repository.FirebaseRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MyHomeViewModel @Inject constructor() : ViewModel() {
    private val repository = FirebaseRepositoryImpl()

    private val _homeChatList = MutableLiveData<MutableList<ChatDTO>>()
    val homeChatList: LiveData<MutableList<ChatDTO>> = _homeChatList

    //NewChat
    private val _chatName = MutableLiveData<String>()
    val chatName: LiveData<String> = _chatName

    private val _participant = MutableLiveData<String>()
    val participant: LiveData<String> = _participant

    private val _participantList =
        MutableLiveData<MutableList<String>>(mutableListOf(FirebaseAuth.getInstance().currentUser?.email.toString()))
    val participantList: LiveData<MutableList<String>> = _participantList

    fun onChatNameChanged(chatName: String) {
        _chatName.value = chatName
    }

    fun onParticipantChanged(participant: String) {
        _participant.value = participant
    }

    fun onParticipantAdded() {
        _participantList.value?.add(participant.value.toString())
    }

    fun chatNameErrorMessage(chatName: String): String {
        var errorMessage = ""
        if (!chatName.isNullOrEmpty() && !chatNameValidator()) {
            if (chatName.length < 6) {
                errorMessage = "Name too short. ${chatName.length}/6"
            } else {
                errorMessage = "Not valid name format. Use only alphanumeric characters"
            }
        }
        return errorMessage
    }

    fun participantErrorMessage(participant: String): String {
        var errorMessage = ""
        if (!participant.isNullOrEmpty() && !participantValidator()) {
            errorMessage = "Not valid email format"
        }
        return errorMessage
    }

    fun chatNameValidator(): Boolean {
        return _chatName.value.toString()
            .matches("[a-zA-Z0-9]+".toRegex()) && _chatName.value.toString().length > 5
    }

    fun participantValidator(): Boolean {
        var validation = false
        if (_participant.value.toString().isNotEmpty()) {
            if (
                android.util.Patterns.EMAIL_ADDRESS.matcher(_participant.value.toString())
                    .matches()
            ) {
                validation = true
            }
        }
        return validation
    }

    fun participantListValidator(): Boolean {
        var validation = false
        if (_participantList.value.toString().isNotEmpty()) {
            validation = true
        }
        return validation
    }

    fun enableCreateChat(): Boolean {
        var boolean = false
        if (chatNameValidator() && participantListValidator()) {
            boolean = true
        }
        return boolean
    }

    @SuppressLint("SuspiciousIndentation")
    fun enableAddParticipant(): Boolean {
        var boolean = true
        if (_participantList.value.toString().isNotEmpty()) {
            if (_participantList.value?.size == 9)
                boolean = false
        }
        return boolean
    }
}