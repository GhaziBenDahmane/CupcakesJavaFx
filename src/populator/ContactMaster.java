/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package populator;

import entity.Claim;
import entity.Contact;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author USER
 */
public class ContactMaster {

    private SimpleStringProperty id;
    private SimpleStringProperty firstName;
    private SimpleStringProperty email;
    private SimpleStringProperty adress;
    private SimpleStringProperty phone;
    private SimpleStringProperty message;
    private Contact contact;

    public ContactMaster(Contact contact) {
        this.contact = contact;
        this.id = new SimpleStringProperty(String.valueOf(contact.getId()));
        this.firstName = firstName = new SimpleStringProperty(contact.getFirstName());
        this.message = message = new SimpleStringProperty(contact.getMessage());
        this.adress = new SimpleStringProperty(contact.getAdress());
        this.phone = new SimpleStringProperty(String.valueOf(contact.getPhone()));
        this.message = new SimpleStringProperty(contact.getMessage());

    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(SimpleStringProperty id) {
        this.id = id;
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(SimpleStringProperty firstName) {
        this.firstName = firstName;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }

    public SimpleStringProperty adressProperty() {
        return adress;
    }

    public void setAdress(SimpleStringProperty adress) {
        this.adress = adress;
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(SimpleStringProperty phone) {
        this.phone = phone;
    }

    public SimpleStringProperty messageProperty() {
        return message;
    }

    public void setMessage(SimpleStringProperty message) {
        this.message = message;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}
