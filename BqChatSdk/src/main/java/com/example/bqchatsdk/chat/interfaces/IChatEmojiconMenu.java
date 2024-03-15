package com.example.bqchatsdk.chat.interfaces;



import com.example.bqchatsdk.models.EaseEmojiconGroupEntity;

import java.util.List;


public interface IChatEmojiconMenu {
    /**
     * Add emoticon group
     * @param groupEntity
     */
    void addEmojiconGroup(EaseEmojiconGroupEntity groupEntity);

    /**
     * Add emoticons list
     * @param groupEntitieList
     */
    void addEmojiconGroup(List<EaseEmojiconGroupEntity> groupEntitieList);

    /**
     * Remove emoticon group
     * @param position
     */
    void removeEmojiconGroup(int position);

    /**
     * Set whether the TabBar is visible
     * @param isVisible
     */
    void setTabBarVisibility(boolean isVisible);

    /**
     * Set up emoticon monitoring
     * @param listener
     */
    void setEmojiconMenuListener(EaseEmojiconMenuListener listener);
}
