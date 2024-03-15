package com.example.bqchatsdk.conversation.presenter;


import com.example.bqchatsdk.conversation.model.EaseConversationInfo;
import com.example.bqchatsdk.interfaces.ILoadDataView;

import java.util.List;


public interface IEaseConversationListView extends ILoadDataView {
    /**
     * Succeeded in obtaining session list data
     * @param data
     */
    void loadConversationListSuccess(List<EaseConversationInfo> data);

    void loadConversationListNoData();

    /**
     * Load conversation failed
     * @param message
     */
    void loadConversationListFail(String message);

    /**
     * Sorted data
     * @param data
     */
    void sortConversationListSuccess(List<EaseConversationInfo> data);

    /**
     * Load mute data for conversation successful
     * @param data
     */
    void loadMuteDataSuccess(List<EaseConversationInfo> data);

    /**
     * Refresh conversation list
     */
    void refreshList();

    /**
     * Refresh the data list at the specified location
     * @param position
     */
    void refreshList(int position);

    /**
     * Delete the specified item
     * @param position
     */
    void deleteItem(int position);

    /**
     * Delete item failed
     * @param position
     * @param message
     */
    void deleteItemFail(int position, String message);
}
