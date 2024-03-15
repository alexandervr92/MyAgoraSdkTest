package com.example.bqchatsdk.adapter.bizq

import android.view.ViewGroup
import com.example.bqchatsdk.chat.adapter.EaseMessageAdapter
import io.agora.chat.ChatMessage

class BizQChatAdapter: EaseMessageAdapter() {

    private val CUSTOM_TYPE = "custom_type"
    private val VIEW_TYPE_MESSAGE_CUSTOM_VIEW_ME = 1000
    private val VIEW_TYPE_MESSAGE_CUSTOM_VIEW_OTHER = 1001

    override fun getItemNotEmptyViewType(position: Int): Int {
        val chatMessage = mData[position]
        val type = chatMessage.getStringAttribute("type", "")

        return when(type){
            CUSTOM_TYPE -> {
                if (chatMessage.direct() == ChatMessage.Direct.SEND) {
                    VIEW_TYPE_MESSAGE_CUSTOM_VIEW_ME
                } else {
                    VIEW_TYPE_MESSAGE_CUSTOM_VIEW_OTHER
                }
            }

            else -> super.getItemNotEmptyViewType(position)
        }
    }

    override fun getViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder<ChatMessage> {
        return if (viewType == VIEW_TYPE_MESSAGE_CUSTOM_VIEW_ME || viewType == VIEW_TYPE_MESSAGE_CUSTOM_VIEW_OTHER) {
            BizQChatCustomTypeViewHolder(
                BizQChatCustomTypeRow(parent!!.context, viewType == VIEW_TYPE_MESSAGE_CUSTOM_VIEW_ME),
                listener
            )
        } else super.getViewHolder(parent, viewType)
    }
}