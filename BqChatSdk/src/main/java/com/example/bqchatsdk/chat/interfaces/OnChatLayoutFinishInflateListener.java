package com.example.bqchatsdk.chat.interfaces;


import com.example.bqchatsdk.chat.EaseChatLayout;
import com.example.bqchatsdk.interfaces.OnTitleBarFinishInflateListener;

public interface OnChatLayoutFinishInflateListener extends OnTitleBarFinishInflateListener {

    /**
     * Callback method after EaseChatLayout initialization
     * @param chatLayout
     */
    default void onChatListFinishInflate(EaseChatLayout chatLayout) {}
}
