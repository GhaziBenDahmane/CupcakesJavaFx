/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.event;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Haroun
 */
public class Event {
    
    private int id ; 
    private String title;
    private String nbPerson;
    private Date startDate;
    private Date endDate;
    private String nbTable ;
    private String band ; 
    private String status ; 
    private String cost;
    public Event() {
    }

    public Event(int id, String title, String nbPerson, Date startDate, Date endDate, String nbTable, String band, String status, String cost) {
        this.id = id;
        this.title = title;
        this.nbPerson = nbPerson;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nbTable = nbTable;
        this.band = band;
        this.status = status;
        this.cost = cost;
    }

 


    public Event(int id, String title, String nbPerson, Date startDate, Date endDate, String nbTable) {
        this.id = id;
        this.title = title;
        this.nbPerson = nbPerson;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nbTable = nbTable;
    }

    public Event(int id, String title, String nbPerson) {
        this.id = id;
        this.title = title;
        this.nbPerson = nbPerson;
    }

    public Event(String title, String nbPerson) {
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

    
   public String getNbPerson() {
        return nbPerson;
    }

    public void setNbPerson(String nbPerson) {
        this.nbPerson = nbPerson;
    }

    public String getNbTable() {
        return nbTable;
    }

    public void setNbTable(String nbTable) {
        this.nbTable = nbTable;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
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
