package com.example.bqchatsdk.helper

import io.agora.CallBack
import io.agora.chat.ChatClient

class BQChatAccountManager(

): IBQChatAccountManager {


    fun signUp(){

    }

    fun logOut(){

    }

    override fun login(userId:String, token:String, listener:((isSuccess:Boolean, errorCode:Int?, errorMsg:String?)->Unit)) {
        //call bizq api -> https://dev.bizq.com/backend/api/v1/operators/agoratoken

        //ALERT -> WORKING ON BACKGROUND
        ChatClient.getInstance().loginWithAgoraToken(userId,token, object : CallBack {
            override fun onSuccess() {
                listener(true,null,null)
            }

            override fun onError(code: Int, error: String?) {
                if (code == 200){
                    listener(true,null,null)
                    return
                }
                if (code == 218 || code == 202){
                    logout(null)
                }

                listener(false,code,error)
            }

        })
    }


    override fun logout(listener:((isSuccess:Boolean,code: Int?, error: String?)->Unit)?) {
        ChatClient.getInstance().logout(true, object : CallBack {
            override fun onSuccess() {
                listener?.invoke(true,null,null)
            }

            override fun onError(code: Int, error: String?) {
                listener?.invoke(false,code,error)
            }

        })
    }

    override fun signup() {
        TODO("Not yet implemented")
    }

    override fun refreshToken() {
        TODO("Not yet implemented")
    }

}