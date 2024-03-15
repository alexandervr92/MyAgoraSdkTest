package com.example.bqchatsdk.chatthread.interfaces;


import com.example.bqchatsdk.chatthread.EaseChatThreadRole;

/**
 * Use to get thread role in {@link EaseChatThreadFragment}
 */
public interface OnChatThreadRoleResultCallback {
    /**
     * The role of thread
     * @param role
     */
    void onThreadRole(EaseChatThreadRole role);
}
