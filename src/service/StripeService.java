/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Arshavin
 */
public class StripeService {
    
    public StripeService(Integer amount) {
        Stripe.apiKey = "sk_test_f7CunmPBjBtkkj9z1vl5H3lr";

        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", amount*100);
        chargeMap.put("currency", "usd");
       chargeMap.put("source", "tok_createDispute");

        try {
            Charge charge = Charge.create(chargeMap);
            System.out.println(charge);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
    }

    

