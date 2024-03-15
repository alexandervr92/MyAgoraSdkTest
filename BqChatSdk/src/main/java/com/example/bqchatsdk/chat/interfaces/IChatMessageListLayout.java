package com.example.bqchatsdk.chat.interfaces;


import com.example.bqchatsdk.chat.adapter.EaseMessageAdapter;
import com.example.bqchatsdk.chat.presenter.EaseChatMessagePresenter;
import com.example.bqchatsdk.chat.widget.EaseChatMessageListLayout;
import com.example.bqchatsdk.interfaces.IRecyclerView;
import com.example.bqchatsdk.interfaces.MessageListItemClickListener;

public interface IChatMessageListLayout extends IRecyclerView {
    
    /**
     * Set custom data presenter
     * @param presenter
     */
    void setPresenter(EaseChatMessagePresenter presenter);

    /**
     * Get message adapter
     * @return
     */
    EaseMessageAdapter getMessageAdapter();

    /**
     * Set the touch monitor in the chat area to determine whether the click is
     * outside the item message or whether the list is being dragged
     * @param listener
     */
    void setOnMessageTouchListener(EaseChatMessageListLayout.OnMessageTouchListener listener);

    /**
     * Set up error monitoring during chat
     * @param listener
     */
    void setOnChatErrorListener(EaseChatMessageListLayout.OnChatErrorListener listener);

    /**
     * Set the click event of each control in the chat list entry
     * @param listener
     */
    void setMessageListItemClickListener(MessageListItemClickListener listener);
}
