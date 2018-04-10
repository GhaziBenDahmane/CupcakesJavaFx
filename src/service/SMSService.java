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

    //public static final String ACCOUNT_SID = "AC1c0bd1ebca8e2ecd2ea5ddc919e950a0";
    //public static final String AUTH_TOKEN = "65063fd6f5f0b228ff625955eb0fb4ca";
    //new account
    public static final String ACCOUNT_SID = "ACd7d32e6a63cf707a24adbe876db4568e";
    public static final String AUTH_TOKEN = "ebd4091acbe07236a639647f58b6de43";

    public static void sendMessage(String to, String text) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber("+1 314-310-3562"),
                    text)
                    .create();

            System.out.println(message.getSid());
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void main(String[] args) {
        sendMessage("+21626879552", "lol");
    }
}
