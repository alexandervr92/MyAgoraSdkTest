package com.example.bqchatsdk.provider;

import android.graphics.drawable.Drawable;

/**
 * The file icon provider
 */
public interface EaseFileIconProvider {
    /**
     * Use filename to provide the icon
     * @param filename
     * @return
     */
    Drawable getFileIcon(String filename);
}
