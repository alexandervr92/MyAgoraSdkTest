package com.example.bqchatsdk.interfaces;


import android.view.View;

import com.example.bqchatsdk.menu.EasePopupWindowHelper;
import com.example.bqchatsdk.menu.MenuItemBean;


public interface IPopupWindow {
    void showItemDefaultMenu(boolean showDefault);

    void clearMenu();

    void addItemMenu(MenuItemBean item);

    void addItemMenu(int groupId, int itemId, int order, String title);

    /**
     * Find the menu object, if the id does not exist, return null
     * @param id
     * @return
     */
    MenuItemBean findItem(int id);

    /**
     * Find item menu by id, and set it to visible or gone
     * @param id
     * @param visible
     */
    void findItemVisible(int id, boolean visible);

    /**
     * Set menu popup window's style
     * @param style
     */
    //void setMenuStyle(EasePopupWindow.Style style);

    /**
     * Set whether show item menu icon
     * @param visible
     */
    void setItemMenuIconVisible(boolean visible);

    void setOnPopupWindowItemClickListener(OnMenuChangeListener listener);

    /**
     * Add header view for message menu
     * @param view
     */
    void addHeaderView(View view);

    /**
     * Hide default reaction view
     * @param hide
     */
    void hideReactionView(boolean hide);

    EasePopupWindowHelper getMenuHelper();
}
