/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package populator;

import entity.Reservation;
import javafx.beans.property.SimpleStringProperty;

public class ReservationMaster {

    private SimpleStringProperty id;
    private SimpleStringProperty nbTables;
    private SimpleStringProperty nbPersonnes;
    private SimpleStringProperty dateReservation;
    private Reservation reservation;

    public ReservationMaster(Reservation reservation) {
        this.reservation = reservation;
        this.id = new SimpleStringProperty(String.valueOf(reservation.getId()));
        this.nbTables = new SimpleStringProperty(String.valueOf(reservation.getNbTables()));
        this.nbPersonnes = new SimpleStringProperty(String.valueOf(reservation.getNbPersonnes()));
        this.dateReservation = new SimpleStringProperty(reservation.getDateReservation().toString());

    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(SimpleStringProperty id) {
        this.id = id;
    }

    public SimpleStringProperty nbTableProperty() {
        return nbTables;
    }

    public void setNbTable(SimpleStringProperty nbTable) {
        this.nbTables = nbTable;
    }

    public SimpleStringProperty nbPersonProperty() {
        return nbPersonnes;
    }

    public void setNbPerson(SimpleStringProperty nbPerson) {
        this.nbPersonnes = nbPerson;
    }

    public SimpleStringProperty dateReservationProperty() {
        return dateReservation;
    }

    public void setDateReservation(SimpleStringProperty dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

}
