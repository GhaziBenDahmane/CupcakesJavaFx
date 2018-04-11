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
public class Reservation {

    private int id, nbTables, nbPersonnes;
    private Date dateReservation;

    public Reservation(int id, int nbTables, int nbPersonnes, Date dateReservation) {
        this.id = id;
        this.nbTables = nbTables;
        this.nbPersonnes = nbPersonnes;
        this.dateReservation = dateReservation;
    }

    public Reservation(int nbTables, int nbPersonnes, Date dateReservation) {
        this.nbTables = nbTables;
        this.nbPersonnes = nbPersonnes;
        this.dateReservation = dateReservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbTables() {
        return nbTables;
    }

    public void setNbTables(int nbTables) {
        this.nbTables = nbTables;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
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
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", nbTables=" + nbTables + ", nbPersonnes=" + nbPersonnes + ", dateReservation=" + dateReservation + '}';
    }

}
