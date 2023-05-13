package com.example.firebasehiltcomposewhasapp.domain.Repository

import androidx.lifecycle.MutableLiveData
import com.example.firebasehiltcomposewhasapp.domain.Chat
import com.example.firebasehiltcomposewhasapp.domain.UserRegister
import com.example.firebasehiltcomposewhasapp.data.dto.ChatDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryImpl: FirebaseRepository {
    val dbRegister =
        Firebase.database("https://fir-hiltcomposewhasapp-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("register")
    val dbChats =
        Firebase.database("https://fir-hiltcomposewhasapp-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("chats")

    val currentUser = FirebaseAuth.getInstance().currentUser?.email

    var chatList = MutableLiveData<MutableList<ChatDTO>>()
    var emailList = ArrayList<String>()//Todo livedata
    var chat = MutableLiveData<Chat>()




    override fun createUserEmailPassword(email:String, password:String, callback: (Boolean)->Unit) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            if(it.isSuccessful){
                if(!FirebaseAuth.getInstance().currentUser?.isEmailVerified!!){
                    FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                    createUserRegister(email)
                    callback(true)
                }
            }else{
                callback(false)
                FirebaseAuth.getInstance().signOut()
            }
        }
    }

    override fun loginUserEmailPassword(email:String, password:String, callback: (Boolean)->Unit) {
        var firebaseAuth = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                if(FirebaseAuth.getInstance().currentUser?.isEmailVerified == true){
                    callback(true)
                }else{
                    //Please verify your account
                    FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                    callback(false)
                }
            }
        }
    }

    override fun createUserRegister(email:String) {
        val userRegister = UserRegister(email)
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
                chatList.postValue(responseChatList)
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

    override fun createChat() {
        TODO("Not yet implemented")
    }

    override fun deleteChat() {
        TODO("Not yet implemented")
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