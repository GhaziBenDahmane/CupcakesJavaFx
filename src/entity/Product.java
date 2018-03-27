/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author Arshavin
 */
public class Product {

    private Integer id;
    private String name;
    private String Type;
    private Double price;
    private Integer nb_view;
    private Integer nb_seller;
    private String Photo;
    private String Description;

    public Product() {
    }

    public Product(Integer id, String name, String Type, Double price, Integer nb_view, Integer nb_seller, String Photo, String Description) {
        this.id = id;
        this.name = name;
        this.Type = Type;
        this.price = price;
        this.nb_view = nb_view;
        this.nb_seller = nb_seller;
        this.Photo = Photo;
        this.Description = Description;
    }

    public Product(String name, String Type, Double price, String Photo, String Description) {

        this.name = name;
        this.Type = Type;
        this.price = price;
        this.Photo = Photo;
        this.Description = Description;
        this.nb_seller = 0;
        this.nb_view = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNb_view() {
        return nb_view;
    }

    public void setNb_view(Integer nb_view) {
        this.nb_view = nb_view;
    }

    public Integer getNb_seller() {
        return nb_seller;
    }

    public void setNb_seller(Integer nb_seller) {
        this.nb_seller = nb_seller;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", Type=" + Type + ", price=" + price + ", nb_view=" + nb_view + ", nb_seller=" + nb_seller + ", Photo=" + Photo + ", Description=" + Description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        return Objects.equals(this.name, other.name);
    }

}
