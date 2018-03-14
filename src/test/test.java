/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Services.EventService;
import entities.event.Event;

/**
 *
 * @author haffez
 */
public class test {
    
      
    public static void main(String[] args) {
      Event  event = new Event("12", "12");
           EventService Es = new EventService();
            Es.add(event);
        
        
    }
    
}
