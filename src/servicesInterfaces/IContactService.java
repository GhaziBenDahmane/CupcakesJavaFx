/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicesInterfaces;

import entity.Contact;

/**
 *
 * @author Anis-PC
 */
public interface IContactService {
    
    void create(Contact contact);

    void update(Contact contact);

    void delete(int id);
}
