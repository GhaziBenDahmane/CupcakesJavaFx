/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javafx.beans.property.StringProperty;

/**
 *
 * @author Anis-PC
 */
public class Contact {

   
    private int phone;
    private String id, firstName, lastName, message, adress, email;
    private boolean status;



    public Contact(String id, int phone, String firstName, String lastName, String adress, String email, boolean status) {
        this.id = id;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.email = email;
        this.status = status;
    }

    public Contact(String id, int phone, String firstName, String lastName, String message, String adress, String email) {
        this.id = id;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
        this.adress = adress;
        this.email = email;
    }
    

    public Contact(String id, int phone, String firstName, String lastName, String message, String adress, String email, boolean status) {
        this.id = id;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
        this.adress = adress;
        this.email = email;
        this.status = status;
    }

    public Contact(int phone, String firstName, String lastName, String adress, String email, boolean status) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.email = email;
        this.status = status;
    }

    public Contact(String id, int phone, String firstName, String lastName, String message, String adress) {
        this.id = id;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
        this.adress = adress;
    }
    

    public Contact(int phone, String firstName, String lastName, String message, String adress, String email, boolean status) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
        this.adress = adress;
        this.email = email;
        this.status = false;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact other = (Contact) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", phone=" + phone + ", firstName=" + firstName + ", lastName=" + lastName + ", message=" + message + ", adress=" + adress + ", email=" + email + '}';
    }

}
