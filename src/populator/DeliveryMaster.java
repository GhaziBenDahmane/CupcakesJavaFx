/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package populator;

import entity.Delivery;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author USER
 */
public class DeliveryMaster {

    private SimpleStringProperty id;
    private SimpleStringProperty deliveryDate;
    private SimpleStringProperty serviceType;
    private SimpleStringProperty email;
    private SimpleStringProperty adress;
    private SimpleStringProperty notes;
    private SimpleStringProperty status;
    private Delivery delivery;

    public DeliveryMaster(Delivery delivery) {
        this.delivery = delivery;
        this.id = new SimpleStringProperty(String.valueOf(delivery.getId()));
        this.deliveryDate = new SimpleStringProperty(String.valueOf(delivery.getDateDelivery()));
        this.serviceType = new SimpleStringProperty(String.valueOf(delivery.getServiceType()));
        this.email = new SimpleStringProperty(String.valueOf(delivery.getEmail()));
        this.adress = new SimpleStringProperty(String.valueOf(delivery.getAdress()));
        this.notes = new SimpleStringProperty(String.valueOf(delivery.getNotes()));
        this.status = new SimpleStringProperty(String.valueOf(delivery.isStatus()));
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(SimpleStringProperty id) {
        this.id = id;
    }

    public SimpleStringProperty deliveryDateProperty() {
        return deliveryDate;
    }

    public void setDeliveryDate(SimpleStringProperty deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public SimpleStringProperty serviceTypeProperty() {
        return serviceType;
    }

    public void setServiceType(SimpleStringProperty serviceType) {
        this.serviceType = serviceType;
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

    public SimpleStringProperty notesProperty() {
        return notes;
    }

    public void setNotes(SimpleStringProperty notes) {
        this.notes = notes;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(SimpleStringProperty status) {
        this.status = status;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

}
