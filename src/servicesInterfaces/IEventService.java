/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicesInterfaces;

import entities.event.Event;

/**
 *
 * @author Haroun
 */
public interface IEventService {
    
    void add(Event event);

    void update(Event event);

    void delete(int id);
    
    
}
