package com.example.bqchatsdk.conversation.interfaces;

import android.graphics.drawable.Drawable;

import com.example.bqchatsdk.conversation.model.EaseConversationSetStyle;
import com.example.bqchatsdk.interfaces.IAvatarSet;


public interface IConversationStyle extends IAvatarSet, IConversationTextStyle {

    void setItemBackGround(Drawable backGround);

    void setItemHeight(int height);

    /**
     * Whether to display unread red dots
     * @param hide
     */
    void hideUnreadDot(boolean hide);

    /**
     * Unread display position
     * Currently supports left and right
     * @param position
     */
    void showUnreadDotPosition(EaseConversationSetStyle.UnreadDotPosition position);

    /**
     * Set unread view's style , see {@link com.example.bqchatsdk.conversation.model.EaseConversationSetStyle.UnreadStyle}
     * @param style
     */
    void setUnreadStyle(EaseConversationSetStyle.UnreadStyle style);
}
