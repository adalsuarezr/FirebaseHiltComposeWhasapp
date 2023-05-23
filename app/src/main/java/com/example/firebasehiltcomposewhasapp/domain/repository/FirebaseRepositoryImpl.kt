package com.example.firebasehiltcomposewhasapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebasehiltcomposewhasapp.domain.UserRegister
import com.example.firebasehiltcomposewhasapp.data.dto.ChatDTO
import com.example.firebasehiltcomposewhasapp.data.dto.MessageDTO
import com.example.firebasehiltcomposewhasapp.data.dto.UserRegisterDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class FirebaseRepositoryImpl : FirebaseRepository {
    val dbRegister =
        Firebase.database("https://fir-hiltcomposewhasapp-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("register")
    val dbChats =
        Firebase.database("https://fir-hiltcomposewhasapp-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("chats")

    val currentUser = FirebaseAuth.getInstance().currentUser?.email.hashCode().toString()

    var chatList = MutableLiveData<MutableList<ChatDTO>>()
    var emailList = ArrayList<String>()//Todo livedata
    private val messages: MutableLiveData<List<MessageDTO>> = MutableLiveData()

    private val _actualChat = MutableStateFlow<ChatDTO?>(null)
    val actualChat: Flow<ChatDTO?> = _actualChat


    override fun onActiveChatChanged(chatId: String) {
        var chatRef = FirebaseDatabase.getInstance().reference.child("chats").child(chatId)
        var childChange = chatRef.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chat = snapshot.getValue(ChatDTO::class.java)
                _actualChat.value =chat
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })}

    override fun getMessages(): LiveData<List<MessageDTO>> {
        TODO("Not yet implemented")
    }

    override fun sendMessage(content: String) {
        TODO("Not yet implemented")
    }

    override fun createUserEmailPassword(
        email: String,
        password: String,
        callback: (Boolean) -> Unit
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    if (!FirebaseAuth.getInstance().currentUser?.isEmailVerified!!) {
                        FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                        createUserRegister(email)
                        callback(true)
                    }
                } else {
                    callback(false)
                    FirebaseAuth.getInstance().signOut()
                }
            }
    }

    override fun loginUserEmailPassword(
        email: String,
        password: String,
        callback: (Boolean) -> Unit
    ) {
        var firebaseAuth = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                        callback(true)
                    } else {
                        //Please verify your account
                        FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                        callback(false)
                    }
                }
            }
    }

    override fun createUserRegister(email: String) {
        val userRegister = UserRegister(email.hashCode().toString())
        dbRegister.child(email.hashCode().toString()).setValue(userRegister)

    }

    override fun deleteUserRegister() {
        TODO("Not yet implemented")
    }

    override fun getChats(): List<ChatDTO> {
        var responseChatList = mutableListOf<ChatDTO>()
        val requestUserRegisteredChats =
            dbRegister.child(Firebase.auth.currentUser?.email.hashCode().toString()).get()
        requestUserRegisteredChats.addOnSuccessListener { requestResponse ->
            val userRegisterChatList =
                requestResponse.getValue(UserRegister::class.java) ?: return@addOnSuccessListener

            if (userRegisterChatList.chatsListIds.isEmpty()) {
                chatList.value = responseChatList
            }
            for (chatId in userRegisterChatList.chatsListIds) {
                val req = dbChats.child(chatId).get()
                req.addOnSuccessListener { chatResponse ->
                    val chat =
                        chatResponse.getValue(ChatDTO::class.java) ?: return@addOnSuccessListener
                    responseChatList.add(chat)

                    if (responseChatList.size == userRegisterChatList.chatsListIds.size) {
                        chatList.postValue(responseChatList)
                    }
                }
            }
        }
        return responseChatList
    }

    override fun createChat(chat: ChatDTO, callback: (Boolean) -> Unit) {
        dbChats.child(chat.id).setValue(chat)

        val emailsProcessed = AtomicInteger(chat.emailList.size)
        val success = AtomicBoolean(true)

        chat.emailList.forEach { email ->
            val userRegisterRef = dbRegister.child(email.hashCode().toString())
            userRegisterRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val participant = snapshot.getValue(UserRegisterDTO::class.java)
                    participant?.let {
                        it.chatsListIds.add(chat.id)
                        userRegisterRef.setValue(it)
                            .addOnFailureListener {
                                success.set(false)
                            }
                            .addOnCompleteListener {
                                if (emailsProcessed.decrementAndGet() == 0) {
                                    callback(success.get())
                                }
                            }
                    } ?: run {
                        if (emailsProcessed.decrementAndGet() == 0) {
                            callback(success.get())
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    if (emailsProcessed.decrementAndGet() == 0) {
                        callback(false)
                    }
                }
            })
        }
    }

    override fun deleteChat() {
        //Create chat in dbChats and unsubscribe all participants
    }

    override fun updateChatNewMessage() {
        TODO("Not yet implemented")
    }

    override fun updateChatNewParticipant() {
        TODO("Not yet implemented")
    }

    override fun updateChatRemoveParticipant() {
        TODO("Not yet implemented")
    }


}