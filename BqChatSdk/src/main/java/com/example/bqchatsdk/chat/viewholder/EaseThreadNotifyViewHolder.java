package com.example.bqchatsdk.chat.viewholder;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.bqchatsdk.constants.EaseConstant;
import com.example.bqchatsdk.interfaces.MessageListItemClickListener;
import com.example.bqchatsdk.manager.EaseActivityProviderHelper;

import io.agora.chat.ChatMessage;


public class EaseThreadNotifyViewHolder extends EaseChatRowViewHolder {

    public EaseThreadNotifyViewHolder(@NonNull View itemView, MessageListItemClickListener itemClickListener) {
        super(itemView, itemClickListener);
    }

    @Override
    public void onBubbleClick(ChatMessage message) {
        // Skip to Chat thread activity
        String parentMsgId = message.getStringAttribute(EaseConstant.EASE_THREAD_PARENT_MSG_ID, "");
        EaseActivityProviderHelper.startToChatThreadActivity(getContext(), message.getMsgId(), parentMsgId, message.getTo());
    }

}
