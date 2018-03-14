/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Services.EventService;
import entities.event.Event;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *
 * @author haffez
 */
public class test {
    
      
    public static void main(String[] args) {
Date mnt = new Date(System.currentTimeMillis()); 

            Event  event = new Event( "Anni", "", mnt , mnt, "", "", "", "");
            EventService Es = new EventService();
            Es.add(event);
        
        
    }
    
}
