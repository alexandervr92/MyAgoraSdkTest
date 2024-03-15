package com.example.bqchatsdk.conversation.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.bqchatsdk.adapter.EaseBaseRecyclerViewAdapter;
import com.example.bqchatsdk.conversation.model.EaseConversationInfo;
import com.example.bqchatsdk.conversation.model.EaseConversationSetStyle;
import com.example.bqchatsdk.conversation.viewholder.EaseConversationType;
import com.example.bqchatsdk.conversation.viewholder.EaseConversationViewHolderFactory;


public class EaseConversationListAdapter extends EaseBaseRecyclerViewAdapter<EaseConversationInfo> {
    private EaseConversationSetStyle style;

    public EaseConversationListAdapter() {}

    public EaseConversationListAdapter(EaseConversationSetStyle style) {
        this.style = style;
    }

    @Override
    public int getItemNotEmptyViewType(int position) {
        return EaseConversationViewHolderFactory.getViewType(mData.get(position));
    }

    @Override
    public ViewHolder<EaseConversationInfo> getViewHolder(ViewGroup parent, int viewType) {
        return EaseConversationViewHolderFactory.createViewHolder(LayoutInflater.from(parent.getContext())
                , parent, EaseConversationType.from(viewType), style);
    }

    public void setConversationSetStyle(EaseConversationSetStyle style) {
        this.style = style;
        notifyDataSetChanged();
    }

}

