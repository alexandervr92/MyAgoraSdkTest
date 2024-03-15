package com.example.myagorasdktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bqchatsdk.adapter.bizq.BizQChatAdapter
import com.example.bqchatsdk.chat.EaseChatFragment
import com.example.bqchatsdk.chat.model.EaseInputMenuStyle
import com.example.bqchatsdk.chat.widget.EaseChatMessageListLayout
import com.example.bqchatsdk.menu.EaseChatType

class ChatConversationActivity : AppCompatActivity() {

    private val watsei = "6422c9748c95a0154637e48d"
    private val laraChic = "603cf3420f53540011c88c0f"



    private val adapter by lazy { BizQChatAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_conversation)

        val conversationID = intent.getStringExtra("conversationID")
        val chatType = intent.getIntExtra("chatType", EaseChatType.SINGLE_CHAT.chatType)


        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment,
                EaseChatFragment.Builder(conversationID, EaseChatType.from(chatType))
                    .useHeader(true)
                    .setHeaderTitle(when(conversationID){
                        watsei -> "Watsei"
                        laraChic -> "Lara Chic"
                        else -> "Desconocido"
                    })
                    .enableHeaderPressBack(true)
                    .setHeaderBackPressListener { onBackPressedDispatcher.onBackPressed() }
                    .getHistoryMessageFromServerOrLocal(false)
                    .setMsgTimeTextSize(12)
                    .showNickname(true)
                    .setMessageListShowStyle(EaseChatMessageListLayout.ShowType.LEFT_RIGHT)
                    .hideReceiverAvatar(false)
                    .hideSenderAvatar(true)
                    .setChatInputMenuStyle(EaseInputMenuStyle.All)
                    .sendMessageByOriginalImage(true)
                    .setEmptyLayout(com.example.bqchatsdk.R.layout.empty_conversation_layout)
                    .setCustomAdapter(adapter)

                    .build())
            .commit()
    }
}