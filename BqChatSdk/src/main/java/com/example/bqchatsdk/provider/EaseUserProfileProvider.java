package com.example.bqchatsdk.provider;


import com.example.bqchatsdk.models.EaseUser;

/**
 * User profile provider
 * @author wei
 *
 */
public interface EaseUserProfileProvider {
    /**
     * return EaseUser for input username
     * @param userID
     * @return
     */
    EaseUser getUser(String userID);
}