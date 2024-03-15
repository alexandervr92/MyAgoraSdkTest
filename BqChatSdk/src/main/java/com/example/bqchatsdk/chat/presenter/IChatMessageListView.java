package com.example.bqchatsdk.chat.presenter;


import com.example.bqchatsdk.interfaces.ILoadDataView;

import java.util.List;

import io.agora.chat.ChatMessage;
import io.agora.chat.ChatRoom;
import io.agora.chat.Conversation;


public interface IChatMessageListView extends ILoadDataView {

    Conversation getCurrentConversation();

    void joinChatRoomSuccess(ChatRoom value);

    void joinChatRoomFail(int error, String errorMsg);

    /**
     * Failed to load message
     * @param error
     * @param message
     */
    void loadMsgFail(int error, String message);

    /**
     * Load local data successfully
     * @param data
     */
    void loadLocalMsgSuccess(List<ChatMessage> data);

    /**
     * Not loaded to local data
     */
    void loadNoLocalMsg();

    /**
     * Load more local data successfully
     * @param data
     */
    void loadMoreLocalMsgSuccess(List<ChatMessage> data);

    /**
     * No more data loaded
     */
    void loadNoMoreLocalMsg();

    /**
     * Load more local historical data
     * @param data
     */
    void loadMoreLocalHistoryMsgSuccess(List<ChatMessage> data, Conversation.SearchDirection direction);

    /**
     * No more local historical data
     */
    void loadNoMoreLocalHistoryMsg();

    /**
     * Load roaming data
     * @param data
     * @param cursor
     */
    void loadServerMsgSuccess(List<ChatMessage> data, String cursor);

    /**
     * Load more roaming data
     * @param data
     * @param cursor
     */
    void loadMoreServerMsgSuccess(List<ChatMessage> data, String cursor);

    /**
     * Refresh current conversation
     * @param data
     */
    void refreshCurrentConSuccess(List<ChatMessage> data, boolean toLatest);

    /**
     * Insert the message to the last of message list
     * @param message
     */
    void insertMessageToLast(ChatMessage message);

    /**
     * Whether thread message list has reached the lasted message
     */
    void reachedLatestThreadMessage();
}
