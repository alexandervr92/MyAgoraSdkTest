package com.example.bqchatsdk.conversation.presenter;


import com.example.bqchatsdk.base.EaseBasePresenter;
import com.example.bqchatsdk.conversation.model.EaseConversationInfo;
import com.example.bqchatsdk.interfaces.ILoadDataView;

import java.util.List;


public abstract class EaseConversationPresenter extends EaseBasePresenter {
    public IEaseConversationListView mView;

    @Override
    public void attachView(ILoadDataView view) {
        mView = (IEaseConversationListView) view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachView();
    }

    /**
     * load data
     */
    public abstract void loadData(boolean fetchConfig);

    /**
     * Sort data
     * @param data
     */
    public abstract void sortData(List<EaseConversationInfo> data);

    /**
     * Make conversation read
     * @param position
     * @param info
     */
    public abstract void makeConversionRead(int position, EaseConversationInfo info);

    /**
     * Pin conversation
     * @param position
     * @param info
     */
    public abstract void makeConversationTop(int position, EaseConversationInfo info);

    /**
     * Unpin conversation
     * @param position
     * @param info
     */
    public abstract void cancelConversationTop(int position, EaseConversationInfo info);

    /**
     * Delete conversation
     * @param position
     * @param info
     */
    public abstract void deleteConversation(int position, EaseConversationInfo info);

}
