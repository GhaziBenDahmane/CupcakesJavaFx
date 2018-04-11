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
 * @author Yasmine
 */
public class Actuality {
    private int id;
    private String title;
    private String content;
    private String photo;
    private Date   date;

    public Actuality(String title, String content, String photo, Date date) {
        this.title = title;
        this.content = content;
        this.photo = photo;
        this.date = date;
    }

    public Actuality(String title) {
        this.title = title;
    }

    public Actuality(int id, String title, String content, String photo, Date date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.photo = photo;
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
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
        final Actuality other = (Actuality) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
