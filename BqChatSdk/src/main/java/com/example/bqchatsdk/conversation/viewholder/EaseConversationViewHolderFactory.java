package com.example.bqchatsdk.conversation.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.bqchatsdk.R;
import com.example.bqchatsdk.adapter.EaseBaseRecyclerViewAdapter;
import com.example.bqchatsdk.conversation.model.EaseConversationInfo;
import com.example.bqchatsdk.conversation.model.EaseConversationSetStyle;
import com.example.bqchatsdk.manager.EaseNotificationMsgManager;

import io.agora.chat.Conversation;


public class EaseConversationViewHolderFactory {
    public static EaseBaseRecyclerViewAdapter.ViewHolder<EaseConversationInfo> createViewHolder(@NonNull LayoutInflater inflater
            , @NonNull ViewGroup parent, EaseConversationType viewType, EaseConversationSetStyle style) {

        switch (viewType) {
            case VIEW_TYPE_CONVERSATION_NOTIFICATION:
                return new EaseNotificationViewHolder(inflater.inflate(R.layout.ease_item_row_chat_history, parent, false), style);
            default:
                return new EaseConversationViewHolder(inflater.inflate(R.layout.ease_item_row_chat_history, parent, false), style);
        }
    }

    public static int getViewType(@NonNull EaseConversationInfo conversationInfo) {
        return getConversationType(conversationInfo).getValue();
    }

    public static EaseConversationType getConversationType(@NonNull EaseConversationInfo conversationInfo) {
        EaseConversationType type;
        Object info = conversationInfo.getInfo();
        if (info instanceof Conversation
                && EaseNotificationMsgManager.getInstance().isNotificationConversation((Conversation) conversationInfo.getInfo())) {
            type = EaseConversationType.VIEW_TYPE_CONVERSATION_NOTIFICATION;
        } else if (info instanceof Conversation) {
            type = EaseConversationType.VIEW_TYPE_CONVERSATION;
        } else {
            type = EaseConversationType.VIEW_TYPE_CONVERSATION_UNKNOWN;
        }
        return type;
    }
}
