/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author ding
 */
public class SMSService {

    public static final String ACCOUNT_SID = "AC1c0bd1ebca8e2ecd2ea5ddc919e950a0";
    public static final String AUTH_TOKEN = "65063fd6f5f0b228ff625955eb0fb4ca";

    public static void sendMessage(String to, String text) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber("+1 928-323-0909"),
                text)
                .create();

        System.out.println(message.getSid());
    }
}
