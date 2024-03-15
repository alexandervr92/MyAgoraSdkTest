package com.example.bqchatsdk.chat.adapter;

import android.view.ViewGroup;

import com.example.bqchatsdk.adapter.EaseBaseRecyclerViewAdapter;
import com.example.bqchatsdk.chat.viewholder.EaseChatViewHolderFactory;
import com.example.bqchatsdk.chat.viewholder.EaseMessageViewType;
import com.example.bqchatsdk.interfaces.MessageListItemClickListener;

import io.agora.chat.ChatMessage;


public class EaseMessageAdapter extends EaseBaseRecyclerViewAdapter<ChatMessage> {
    protected MessageListItemClickListener listener;
    
    public EaseMessageAdapter() {}
    
    public EaseMessageAdapter(MessageListItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemNotEmptyViewType(int position) {
        return EaseChatViewHolderFactory.getViewType(mData.get(position));
    }

    @Override
    public ViewHolder<ChatMessage> getViewHolder(ViewGroup parent, int viewType) {
        return EaseChatViewHolderFactory.createViewHolder(parent, EaseMessageViewType.from(viewType), listener);
    }
    
    public void setListItemClickListener(MessageListItemClickListener listener) {
        this.listener = listener;
        notifyDataSetChanged();
    }
}
