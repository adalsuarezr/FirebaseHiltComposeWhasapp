package com.example.firebasehiltcomposewhasapp.presentation.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebasehiltcomposewhasapp.data.dto.ChatDTO
import com.example.firebasehiltcomposewhasapp.data.dto.MessageDTO
import com.example.firebasehiltcomposewhasapp.domain.repository.FirebaseRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MyHomeViewModel @Inject constructor() : ViewModel() {

    private val repository = FirebaseRepositoryImpl()

    private val _actualChat = MutableStateFlow<ChatDTO?>(null)
    val actualChat: StateFlow<ChatDTO?> = _actualChat

    private val _goToChatId = MutableLiveData<String>("")
    val goToChatId: LiveData<String> = _goToChatId

    init {
        repository.getChats()
    }

    private val mAuth = FirebaseAuth.getInstance()

    val userId = mAuth.currentUser?.email

    private val _homeChatList = repository.chatList
    val homeChatList: LiveData<MutableList<ChatDTO>> = _homeChatList

    //NewChat
    private val _chatName = MutableLiveData<String>()
    val chatName: LiveData<String> = _chatName

    private val _participant = MutableLiveData<String>()
    val participant: LiveData<String> = _participant

    private val _participantList =
        MutableLiveData<MutableList<String>>().apply { value = mutableListOf() }
    val participantList: LiveData<MutableList<String>> = _participantList

    private val _addParticipantButtonEnabled = MutableLiveData<Boolean>()
    val addParticipantButtonEnabled: LiveData<Boolean> = _addParticipantButtonEnabled

    fun onChatNameChanged(chatName: String) {
        _chatName.value = chatName
    }

    fun onParticipantChanged(participant: String) {
        _participant.value = participant
    }

    fun onAddParticipantClicked() {
        _participantList.value?.add(participant.value.toString())
        Log.i("valor de participantes añadidos", _participantList.value.toString())
        _participant.value = ""
    }

    fun onChatItemClicked(chatId: String) {
        _goToChatId.value = chatId
        _actualChat.value = homeChatList.value?.find { it.id == chatId }
        repository.onActiveChatChanged(chatId)
    }

    suspend fun onCreateChatClicked(): Boolean = withContext(Dispatchers.IO) {
        val participants = _participantList.value
        participants?.add(mAuth.currentUser?.email!!.toString())
        val id: String = (_chatName.value + setOf(participants).toString()).hashCode().toString()
        val chat =
            ChatDTO(id, _chatName.value.toString(), participants!!, arrayListOf<MessageDTO>())

        return@withContext suspendCoroutine<Boolean> { continuation ->
            repository.createChat(chat) { isSuccess ->
                _participantList.value = mutableListOf()
                continuation.resume(isSuccess)
            }
        }
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
        } else {
            if (participantValidator() && _participantList.value?.size == 9) {
                errorMessage = "Maximum number of participants reached"
            }
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

    fun enableCreateChatButton(): Boolean {
        var boolean = false
        if (chatNameValidator() && participantList.value.toString().isNotEmpty()) {
            boolean = true
        }
        return boolean
    }

    @SuppressLint("SuspiciousIndentation")
    fun enableAddParticipantButton(): Boolean {
        var boolean = true
        if (_participantList.value.toString().isNotEmpty()) {
            if (_participantList.value?.size == 9)
                boolean = false
        }
        return boolean
    }

    fun onMessageChanged(newValue: String) {

    }

    fun onSendMessageClicked() {
        TODO("Not yet implemented")
    }

    fun enableSendMessageButton(): Boolean {
        return true
    }
}
