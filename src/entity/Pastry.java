/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author haffez
 */
public class Pastry {
        private int id;
        private int users_id;
        private String address;
        private int nb_table;

    public Pastry(String address, int nb_table) {
        this.address = address;
        this.nb_table = nb_table;
    }

    public Pastry(int id, int users_id, String address, int nb_table) {
        this.id = id;
        this.users_id = users_id;
        this.address = address;
        this.nb_table = nb_table;
    }

    public Pastry(int users_id, String address, int nb_table) {
        this.users_id = users_id;
        this.address = address;
        this.nb_table = nb_table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNb_table() {
        return nb_table;
    }

    public void setNb_table(int nb_table) {
        this.nb_table = nb_table;
    }
        
}
