package com.example.bqchatsdk.chat.viewholder;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.bqchatsdk.interfaces.MessageListItemClickListener;
import com.example.bqchatsdk.manager.EaseConfigsManager;

import io.agora.chat.ChatClient;
import io.agora.chat.ChatMessage;
import io.agora.exceptions.ChatException;


public class EaseExpressionViewHolder extends EaseChatRowViewHolder {

    public EaseExpressionViewHolder(@NonNull View itemView, MessageListItemClickListener itemClickListener) {
        super(itemView, itemClickListener);
    }

    @Override
    protected void handleReceiveMessage(ChatMessage message) {
        super.handleReceiveMessage(message);
        if(!EaseConfigsManager.enableSendChannelAck()) {
            //Here no longer send read_ack message separately, instead enter the chat page to send channel_ack
            //New messages are sent in the onReceiveMessage method of the chat page, except for video
            // , voice and file messages, and send read_ack messages
            if (!message.isAcked() && message.getChatType() == ChatMessage.ChatType.Chat) {
                try {
                    ChatClient.getInstance().chatManager().ackMessageRead(message.getFrom(), message.getMsgId());
                } catch (ChatException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
