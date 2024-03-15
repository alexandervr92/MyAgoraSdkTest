package com.example.bqchatsdk.helper

interface IBQChatAccountManager {
    fun login(userId:String, token:String, listener:((isSuccess:Boolean, errorCode:Int?, errorMsg:String?)->Unit))
    fun logout(listener:((isSuccess:Boolean,code: Int?, error: String?)->Unit)?)
    fun signup()
    fun refreshToken()
}