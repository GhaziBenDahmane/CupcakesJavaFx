/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Haroun
 */
public class Event {

    private int id;
    private String title;
    private int nbPerson;
    private Date startDate;
    private Date endDate;
    private int nbTable;
    private int band;
    private String status;
    private double cost;

    public Event() {
    }

    public Event(String title, int nbPerson, Date startDate, Date endDate, int nbTable, int band, String status, Double cost) {
        this.title = title;
        this.nbPerson = nbPerson;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nbTable = nbTable;
        this.band = band;
        this.status = status;
        this.cost = cost;
    }

    public Event(int id, String title, int nbPerson, Date startDate, Date endDate, int nbTable) {
        this.id = id;
        this.title = title;
        this.nbPerson = nbPerson;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nbTable = nbTable;
    }

    public Event(int id, String title, int nbPerson) {
        this.id = id;
        this.title = title;
        this.nbPerson = nbPerson;
    }

    public Event(String title, int nbPerson) {
        this.title = title;
        this.nbPerson = nbPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", title=" + title + ", nbPerson=" + nbPerson + ", startDate=" + startDate + ", endDate=" + endDate + ", nbTable=" + nbTable + ", band=" + band + '}';
    }

    public int getNbPerson() {
        return nbPerson;
    }

    public void setNbPerson(int nbPerson) {
        this.nbPerson = nbPerson;
    }

    public int getNbTable() {
        return nbTable;
    }

    public void setNbTable(int nbTable) {
        this.nbTable = nbTable;
    }

    public int getBand() {
        return band;
    }

    public void setBand(int band) {
        this.band = band;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        return hash;
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
        final Event other = (Event) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }

}
