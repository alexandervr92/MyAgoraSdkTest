package com.example.bqchatsdk.interfaces;

import android.widget.PopupWindow;

import com.example.bqchatsdk.menu.EasePopupWindowHelper;
import com.example.bqchatsdk.menu.MenuItemBean;

import io.agora.chat.ChatMessage;


public interface OnMenuChangeListener {
    /**
     * Before showing the Menu
     * @param helper
     * @param message
     */
    void onPreMenu(EasePopupWindowHelper helper, ChatMessage message);

    /**
     * Item click
     * @param item
     * @param message
     */
    boolean onMenuItemClick(MenuItemBean item, ChatMessage message);

    /**
     * Dismiss event
     * @param menu
     */
    default void onDismiss(PopupWindow menu) {}
}