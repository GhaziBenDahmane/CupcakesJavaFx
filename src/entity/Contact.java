/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Anis-PC
 */
public class Contact {

   
    private int id, phone;
   
    private String firstName,message, adress;
    private boolean status;

    public Contact() {
    }

    public Contact(int id, int phone, String firstName, String message, String adress, boolean status) {
        this.id = id;
        this.phone = phone;
        this.firstName = firstName;
        this.message = message;
        this.adress = adress;

        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", phone=" + phone + ", firstName=" + firstName + ", message=" + message + ", adress=" + adress + ", status=" + status + '}';
    }



    
}
