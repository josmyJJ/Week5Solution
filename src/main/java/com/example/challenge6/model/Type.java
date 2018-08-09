package com.example.challenge6.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String categorie;

    @OneToMany(mappedBy = "type",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    public Set<Car> car;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategories() {
        return categorie;
    }

    public void setCategories(String categories) {
        this.categorie = categories;
    }


    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Set<Car> getCar() {
        return car;
    }

    public void setCar(Set<Car> car) {
        this.car = car;
    }
}
