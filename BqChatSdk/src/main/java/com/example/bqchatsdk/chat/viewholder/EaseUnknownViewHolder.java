package com.example.bqchatsdk.chat.viewholder;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.bqchatsdk.interfaces.MessageListItemClickListener;
import com.example.bqchatsdk.manager.EaseConfigsManager;
import com.example.bqchatsdk.manager.EaseDingMessageHelper;

import io.agora.chat.ChatClient;
import io.agora.chat.ChatMessage;
import io.agora.exceptions.ChatException;


public class EaseUnknownViewHolder extends EaseChatRowViewHolder{

    public EaseUnknownViewHolder(@NonNull View itemView, MessageListItemClickListener itemClickListener) {
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
                return;
            }
        }

        // Send the group-ack cmd type msg if this msg is a ding-type msg.
        EaseDingMessageHelper.get().sendAckMessage(message);
    }
}
