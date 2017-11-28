package com.example.service;

import com.example.framework.data.BaseService;
import com.example.model.Job;
import com.example.model.User;

/**
 * @author: kameshr
 */
public interface MailJobService extends BaseService<Job, Long> {

    /**
     * Sends the confirmation mail to user.
     *
     * @param user
     */
    public void sendConfirmationMail(User user);
}
