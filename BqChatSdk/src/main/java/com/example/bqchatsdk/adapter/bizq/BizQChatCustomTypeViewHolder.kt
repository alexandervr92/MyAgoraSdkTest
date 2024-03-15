package com.example.bqchatsdk.adapter.bizq

import android.view.View
import com.example.bqchatsdk.chat.viewholder.EaseChatRowViewHolder
import com.example.bqchatsdk.interfaces.MessageListItemClickListener
import io.agora.chat.ChatMessage

class BizQChatCustomTypeViewHolder(itemView: View, itemClickListener: MessageListItemClickListener): EaseChatRowViewHolder(itemView,itemClickListener) {

    override fun onBubbleClick(message: ChatMessage?) {
        super.onBubbleClick(message)
    }
}