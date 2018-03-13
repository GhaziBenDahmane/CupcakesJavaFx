/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Connection.DataSource;
import Entities.Contact.Contact;
import java.sql.Connection;
import servicesInterfaces.IContactService;

/**
 *
 * @author Anis-PC
 */
public class ContactService implements IContactService{

    private Connection connection;

    public ContactService() {
         connection = DataSource.getInstance().getConnection();
    }
    
    

    @Override
    public void create(Contact contact) {
       
    }

    @Override
    public void update(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
