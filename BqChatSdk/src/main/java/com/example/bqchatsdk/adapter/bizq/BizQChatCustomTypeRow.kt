package com.example.bqchatsdk.adapter.bizq

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.example.bqchatsdk.R
import com.example.bqchatsdk.widget.chatrow.EaseChatRow
import io.agora.chat.ChatMessage

@SuppressLint("ViewConstructor")
class BizQChatCustomTypeRow: EaseChatRow {

    private lateinit var contentView:TextView

    constructor(context: Context?, isSender: Boolean) : super(context, isSender)
    constructor(context: Context?, message: ChatMessage?, position: Int, adapter: Any?) : super(
        context,
        message,
        position,
        adapter
    )

    override fun onInflateView() {
        inflater.inflate(
            if (!showSenderType) R.layout.layout_row_received_custom_type else R.layout.layout_row_sent_custom_type,
            this
        )
    }

    override fun onFindViewById() {
        contentView = findViewById(R.id.tv_chatcontent)
    }

    override fun onSetUpView() {
//        TextMessageBody txtBody = (TextMessageBody) message.getBody();
//        if(txtBody != null){
//            contentView.setText(txtBody.getMessage());
//        }
    }
}