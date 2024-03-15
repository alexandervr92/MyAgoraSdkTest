package com.example.bqchatsdk.conversation.interfaces;

public interface OnConversationChangeListener {
    /**
     * Notice item change
     * @param position
     */
    void notifyItemChange(int position);

    /**
     * Notify all data changes
     */
    void notifyAllChange();

    /**
     * Notice removal
     * @param position
     */
    void notifyItemRemove(int position);
}
