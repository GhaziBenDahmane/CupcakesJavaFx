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
public class Delivery {

    private int id,phone;
    private String name,notes, email, adress, serviceType;
    private Date dateDelivery,contactTime;
    private boolean status;

    public Delivery() {
    }

    public Delivery(int id, int phone, String name, String notes, String email, String adress, String serviceType, Date dateDelivery, Date contactTime, boolean status) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.notes = notes;
        this.email = email;
        this.adress = adress;
        this.serviceType = serviceType;
        this.dateDelivery = dateDelivery;
        this.contactTime = contactTime;
        this.status = status;
    }

    public Delivery(int id, boolean status) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public Date getContactTime() {
        return contactTime;
    }

    public void setContactTime(Date contactTime) {
        this.contactTime = contactTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Delivery{" + "id=" + id + ", phone=" + phone + ", name=" + name + ", notes=" + notes + ", email=" + email + ", adress=" + adress + ", serviceType=" + serviceType + ", dateDelivery=" + dateDelivery + ", contactTime=" + contactTime + ", status=" + status + '}';
    }
    

}
