package com.example.bqchatsdk.chatthread.interfaces;

public interface OnChatThreadCreatedResultListener {

    /**
     * Thread created successfully
     * @param parentMsgId
     * @param threadId
     * @return
     */
    boolean onThreadCreatedSuccess(String parentMsgId, String threadId);

    /**
     * Failed to create thread
     * @param code
     * @param message
     */
    void onThreadCreatedFail(int code, String message);
}
