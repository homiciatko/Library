package com.homik.service;

/**
 * Created by Pawel on 2017-04-08.
 */
public interface EmailService {

    void sendEmail(String fromAddress, String toAddress, String subject, String body);
}
