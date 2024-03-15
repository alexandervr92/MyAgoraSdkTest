package com.example.myagorasdktest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bqchatsdk.chat.EaseChatFragment
import com.example.bqchatsdk.interfaces.OnChatPrimaryMenuItemClickListener
import com.example.bqchatsdk.menu.EaseChatType

class ChatActivity : AppCompatActivity() {
    companion object{
        fun actionStart(context: Context, conversationID: String?, chatType: EaseChatType) {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("conversationID", conversationID)
            intent.putExtra("chatType", chatType.chatType)
            context.startActivity(intent)
        }
    }

    private val primaryItemClickListener = OnChatPrimaryMenuItemClickListener {
        TODO("Not yet implemented")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val conversationID = intent.getStringExtra("conversationID")
        val chatType = intent.getIntExtra("chatType", EaseChatType.SINGLE_CHAT.chatType)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment,
                EaseChatFragment.Builder(conversationID, EaseChatType.from(chatType))
                    .useHeader(true)
                    .setHeaderTitle(conversationID)
                    .enableHeaderPressBack(true)
                    .setOnPrimaryMenuItemClickListener(primaryItemClickListener)
                    .setHeaderBackPressListener { onBackPressed() }
                    .build())
            .commit()
    }
}