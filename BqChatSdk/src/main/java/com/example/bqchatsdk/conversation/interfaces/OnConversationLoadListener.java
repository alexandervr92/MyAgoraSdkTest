package com.example.bqchatsdk.conversation.interfaces;


import com.example.bqchatsdk.conversation.model.EaseConversationInfo;

import java.util.List;


public interface OnConversationLoadListener {
    /**
     * Call back after loading
     * @param data
     */
    void loadDataFinish(List<EaseConversationInfo> data);

    /**
     * Call back after failed to load data
     * @param message
     */
    default void loadDataFail(String message) {}

}
