package com.example.bqchatsdk.interfaces;


import com.example.bqchatsdk.widget.EaseTitleBar;

public interface OnTitleBarFinishInflateListener {
    /**
     * Callback method after TitleBar initialization
     * @param titleBar
     */
    default void onTitleBarFinishInflate(EaseTitleBar titleBar) {}
}
