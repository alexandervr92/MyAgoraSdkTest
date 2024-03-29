package com.example.bqchatsdk.chat.viewholder;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.bqchatsdk.chat.adapter.EaseMessageAdapter;
import com.example.bqchatsdk.interfaces.MessageListItemClickListener;
import com.example.bqchatsdk.widget.chatrow.EaseChatRow;

import java.util.List;

import io.agora.chat.ChatMessage;


public class EaseChatRowViewHolder extends EaseMessageAdapter.ViewHolder<ChatMessage> implements EaseChatRow.EaseChatRowActionCallback {
    private static final String TAG = EaseChatRowViewHolder.class.getSimpleName();
    private Context context;
    private EaseChatRow chatRow;
    private ChatMessage message;
    private MessageListItemClickListener mItemClickListener;

    public EaseChatRowViewHolder(@NonNull View itemView, MessageListItemClickListener itemClickListener) {
        super(itemView);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(params);
        this.context = itemView.getContext();
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public void initView(View itemView) {
        this.chatRow = (EaseChatRow) itemView;
    }

    @Override
    public void setData(ChatMessage item, int position) {
        message = item;
        chatRow.setUpView(item, position, mItemClickListener, this);
        handleMessage();
    }

    @Override
    public void setDataList(List<ChatMessage> data, int position) {
        super.setDataList(data, position);
        chatRow.setTimestamp(position == 0 ? null : data.get(position - 1));
    }

    @Override
    public void onResendClick(ChatMessage message) {

    }

    @Override
    public void onBubbleClick(ChatMessage message) {

    }

    @Override
    public void onDetachedFromWindow() {

    }

    private void handleMessage() {
        if (message.direct() == ChatMessage.Direct.SEND) {
            handleSendMessage(message);
        } else if (message.direct() == ChatMessage.Direct.RECEIVE) {
            handleReceiveMessage(message);
        }
    }

    /**
     * send message
     * @param message
     */
    protected void handleSendMessage(final ChatMessage message) {
        // Update the view according to the message current status.
        getChatRow().updateView(message);
    }

    /**
     * receive message
     * @param message
     */
    protected void handleReceiveMessage(ChatMessage message) {

    }

    public Context getContext() {
        return context;
    }

    public EaseChatRow getChatRow() {
        return chatRow;
    }
}
